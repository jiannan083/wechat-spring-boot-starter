package cn.bfay.wechat;

import cn.bfay.okhttp.OkHttpUtils;
import cn.bfay.wechat.model.TemplateMessage;
import cn.bfay.wechat.model.TemplateMessageNoticeData;
import cn.bfay.wechat.model.WechatAccessToken;
import cn.bfay.wechat.model.WechatPageAccessToken;
import cn.bfay.wechat.model.WechatServerIp;
import cn.bfay.wechat.model.WechatUserInfo;
import cn.bfay.wechat.model.menu.Menu;
import cn.bfay.wechat.model.message.Article;
import cn.bfay.wechat.model.message.RequestMessage;
import cn.bfay.wechat.model.message.ResponseNewsMessage;
import cn.bfay.wechat.model.message.ResponseTextMessage;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

//import cn.bfay.util.JsonUtil;

/**
 * WechatCoreUtil.
 *
 * @author wangjiannan
 * @since 2019/10/24
 */
public class WechatCoreUtil {
    private static final Logger log = LoggerFactory.getLogger(WechatCoreUtil.class);

    private static final String BASE_URL = "https://api.weixin.qq.com";

    private final String appid;
    private final String secret;
    private final String token;
    private final Map<String, String> templates;

    public WechatCoreUtil(String appid, String secret, String token, Map<String, String> templates) {
        this.appid = appid;
        this.secret = secret;
        this.token = token;
        this.templates = templates;
    }

    /**
     * 接入校验.
     *
     * @param request request
     * @return string
     */
    public String coreCheck(HttpServletRequest request) {
        try {
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (checkSignature(token, request.getParameter("signature"),
                    request.getParameter("timestamp"), request.getParameter("nonce"))) {
                return request.getParameter("echostr");
            } else {
                return "";
            }
        } catch (Exception e) {
            log.error("wechat get core error", e);
            return "";
        }
    }

    ///**
    // * 消息解析处理.
    // *
    // * @param request request
    // * @return {@link RequestMessage}
    // */
    //public RequestMessage coreProcess(HttpServletRequest request) {
    //    return WechatMessageUtils.parseRequestXml(request);
    //}

    /**
     * 获取access_token.
     *
     * @return {@link WechatAccessToken}
     */
    public WechatAccessToken getAccessToken() {
        String url = BASE_URL + "/cgi-bin/token";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("grant_type", "client_credential");
        paramMap.put("appid", appid);
        paramMap.put("secret", secret);
        return OkHttpUtils.executeGet(url, paramMap, WechatAccessToken.class);
    }

    /**
     * 获取用户基本信息（包括UnionID机制）.
     *
     * @param accessToken accessToken
     * @param openid      openid
     * @return {@link WechatUserInfo}
     */
    public WechatUserInfo getUserInfo(String accessToken, String openid) {
        String url = BASE_URL + "/cgi-bin/user/info";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("access_token", accessToken);
        paramMap.put("openid", openid);
        paramMap.put("lang", "zh_CN");
        return OkHttpUtils.executeGet(url, paramMap, WechatUserInfo.class);
    }

    /**
     * 获取微信用户信息（页面方式获取）.
     * <p>
     * 拉取用户信息(需scope为 snsapi_userinfo)
     * {"errcode":40003,"errmsg":" invalid openid "}
     * </p>
     *
     * @param code code
     * @return {@link WechatUserInfo}
     */
    public WechatUserInfo getUserInfo(String code) {
        WechatPageAccessToken wechatPageAccessToken = getPageAccessToken(code);

        String url = BASE_URL + "/sns/userinfo";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("access_token", wechatPageAccessToken.getAccessToken());
        paramMap.put("openid", wechatPageAccessToken.getOpenid());
        paramMap.put("lang", "zh_CN");
        return OkHttpUtils.executeGet(url, paramMap, WechatUserInfo.class);
    }

    /**
     * 获取网页授权access_token.
     * <p>
     * 通过code换取网页授权access_token
     * {"errcode":40029,"errmsg":"invalid code"}
     * 这里通过code换取的是一个特殊的网页授权access_token,与基础支持中的access_token
     * （该access_token用于调用其他接口）不同。公众号可通过下述接口来获取网页授权access_token。
     * 如果网页授权的作用域为snsapi_base，则本步骤中获取到网页授权access_token的同时，
     * 也获取到了openid，
     * </p>
     *
     * @param code code
     * @return {@link WechatPageAccessToken}
     */
    public WechatPageAccessToken getPageAccessToken(String code) {
        String url = BASE_URL + "/sns/oauth2/access_token";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("appid", appid);
        paramMap.put("secret", secret);
        paramMap.put("code", code);
        paramMap.put("grant_type", "authorization_code");
        return OkHttpUtils.executeGet(url, paramMap, WechatPageAccessToken.class);
    }

    /**
     * 获取微信服务ip列表.
     *
     * @param accessToken accessToken
     * @return list
     */
    public List<String> getWechatServerIp(String accessToken) {
        String url = BASE_URL + "/cgi-bin/getcallbackip";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("access_token", accessToken);
        WechatServerIp wechatServerIp = OkHttpUtils.executeGet(url, paramMap, WechatServerIp.class);
        return wechatServerIp.getIps();
    }

    ///**
    // * 发送模板消息.
    // *
    // * @param accessToken     access_token
    // * @param templateMessage {@link TemplateMessage}
    // */
    //public void sendTemplateMessage(String accessToken, TemplateMessage templateMessage) {
    //    String url = BASE_URL + "/cgi-bin/message/template/send?access_token=" + accessToken;
    //    String result = OkHttpUtils.executePost(url, bean2Json(templateMessage), String.class);
    //}

    /**
     * 发送模板消息.
     *
     * @param accessToken   access_token
     * @param openid        openid
     * @param templateName  templateName
     * @param promotionLink promotionLink
     * @param params        参数
     */
    public void sendTemplateMessage(String accessToken, String openid, String templateName,
                                    String promotionLink, List<Map<String, String>> params) {
        String url = BASE_URL + "/cgi-bin/message/template/send?access_token=" + accessToken;
        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTouser(openid);
        templateMessage.setTemplateId(templates.get(templateName));
        templateMessage.setUrl(promotionLink);
        templateMessage.setData(transParams(params));
        String result = OkHttpUtils.executePost(url, bean2Json(templateMessage), String.class);
    }

    private TemplateMessageNoticeData transParams(List<Map<String, String>> params) {
        try {
            TemplateMessageNoticeData templateMessageNoticeData = new TemplateMessageNoticeData();
            for (int i = 0; i < params.size(); i++) {
                TemplateMessageNoticeData.Keyword keyword = new TemplateMessageNoticeData.Keyword();
                keyword.setValue(params.get(i).get("value"));
                keyword.setColor(params.get(i).get("color"));

                Field field = templateMessageNoticeData.getClass().getDeclaredField(params.get(i).get("name"));
                field.setAccessible(true);
                field.set(templateMessageNoticeData, keyword);
            }
            return templateMessageNoticeData;
        } catch (Exception e) {
            log.error("模板消息属性转换设置失败", e);
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        TemplateMessageNoticeData templateMessageNoticeData = new TemplateMessageNoticeData();

        Field field = templateMessageNoticeData.getClass().getDeclaredField("keyword" + 1);
        System.out.println(field);
        //for (int i=0;i<fields.length;i++){
        //    System.out.println(fields[i]);
        //}
        field.setAccessible(true);
        field.set(templateMessageNoticeData, new TemplateMessageNoticeData.Keyword());

        System.out.println(templateMessageNoticeData);
    }


    /**
     * 自定义菜单创建接口.
     *
     * @param accessToken accessToken
     * @param menu        menu
     */
    public void createMenu(String accessToken, Menu menu) {
        String url = BASE_URL + "/cgi-bin/menu/create?access_token=" + accessToken;
        String result = OkHttpUtils.executePost(url, bean2Json(menu), String.class);
    }

    public static final String SCOPE_TYPE_BASE = "snsapi_base";
    public static final String SCOPE_TYPE_USERINFO = "snsapi_userinfo";

    /**
     * 生成授权url.
     *
     * @param redirectUri redirectUri
     * @return string
     */
    public String generateAuthUrl(String redirectUri) {
        return generateAuthUrl(redirectUri, SCOPE_TYPE_USERINFO);
    }

    /**
     * 生成授权url.
     *
     * @param redirectUri redirectUri
     * @param scope       scope
     * @return string
     */
    public String generateAuthUrl(String redirectUri, String scope) {
        try {
            return String.format("https://open.weixin.qq.com/connect/oauth2/authorize?" +
                    "appid=%s" +
                    "&redirect_uri=%s" +
                    "&response_type=code" +
                    "&scope=%s" +
                    "&state=%s" +
                    "#wechat_redirect", appid, URLEncoder.encode(redirectUri, "UTF-8"), scope, "");
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    private boolean checkSignature(String token, String signature, String timestamp, String nonce)
            throws Exception {
        String[] arr = new String[]{token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        // 将三个参数字符串拼接成一个字符串进行sha1加密
        byte[] digest = md.digest(content.toString().getBytes());
        String tmpStr = byteToStr(digest);
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return signature.equalsIgnoreCase(tmpStr);
    }

    private String byteToStr(byte[] byteArray) {// 将字节数组转换为十六进制字符串
        StringBuilder strDigest = new StringBuilder();
        for (byte aByteArray : byteArray) {
            strDigest.append(byteToHexStr(aByteArray));
        }
        return strDigest.toString();
    }

    private String byteToHexStr(byte mByte) {// 将字节转换为十六进制字符串
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        return new String(tempArr);
    }

    //------------------------- 消息处理 -------------------------

    // 请求消息类型：文本
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    // 请求消息类型：图片
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    // 请求消息类型：音频
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    // 请求消息类型：视频消息
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    // 请求消息类型：小视频消息
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
    // 请求消息类型：地理位置
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    // 请求消息类型：链接
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    // 请求消息类型：推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    // 事件类型：subscribe(订阅)
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    // 事件类型：unsubscribe(取消订阅)
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    // 事件类型：CLICK(自定义菜单点击事件)
    public static final String EVENT_TYPE_CLICK = "CLICK";
    // 模板消息
    public static final String EVENT_TYPE_TEMPLATESENDJOBFINISH = "TEMPLATESENDJOBFINISH";

    // 返回消息类型：文本
    private static final String RESP_MESSAGE_TYPE_TEXT = "text";
    // 返回消息类型：音乐
    private static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    // 返回消息类型：图文
    private static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 解析微信发来的请求（XML）-借助于开源框架dom4j去解析xml.
     *
     * @param request req
     * @return {@link RequestMessage}
     */
    public RequestMessage coreProcess(HttpServletRequest request) {
        InputStream inputStream = null;
        try {
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            request.setCharacterEncoding("UTF-8");
            // 将解析结果存储在HashMap中
            Map<String, String> map = new HashMap<>();
            // 从request中取得输入流
            inputStream = request.getInputStream();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elements = root.elements();
            // 遍历所有子节点
            for (Element e : elements) {
                map.put(e.getName(), e.getText());
            }
            return json2Bean(bean2Json(map), RequestMessage.class);
        } catch (Exception e) {
            throw new RuntimeException("parseRequestXml error");
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 回复文本消息.
     *
     * @param fromUserName fromUserName
     * @param toUserName   toUserName
     * @param respContent  respContent
     * @return string
     */
    public String responseTextMessage(String fromUserName, String toUserName, String respContent) {
        ResponseTextMessage responseTextMessage = new ResponseTextMessage();
        responseTextMessage.setToUserName(fromUserName);
        responseTextMessage.setFromUserName(toUserName);
        responseTextMessage.setCreateTime(new Date().getTime());
        responseTextMessage.setMsgType(RESP_MESSAGE_TYPE_TEXT);
        responseTextMessage.setContent(respContent);
        // 文本消息对象转换成xml.
        xstream.alias("xml", ResponseTextMessage.class);
        return xstream.toXML(responseTextMessage);
    }

    /**
     * 回复图文消息.
     *
     * @param fromUserName fromUserName
     * @param toUserName   toUserName
     * @param articles     articles
     * @return string
     */
    public String responseNewsMessage(String fromUserName, String toUserName, List<Article> articles) {
        ResponseNewsMessage responseNewsMessage = new ResponseNewsMessage();
        responseNewsMessage.setToUserName(fromUserName);
        responseNewsMessage.setFromUserName(toUserName);
        responseNewsMessage.setCreateTime(new Date().getTime());
        responseNewsMessage.setMsgType(RESP_MESSAGE_TYPE_NEWS);
        responseNewsMessage.setArticleCount(articles.size());
        responseNewsMessage.setArticles(articles);
        // 图文消息对象转换成xml.
        xstream.alias("xml", ResponseNewsMessage.class);
        xstream.alias("item", Article.class);
        return xstream.toXML(responseNewsMessage);
    }

    /**
     * 扩展xstream，使其支持CDATA块.
     */
    private final XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    // ------------------------------- json -------------------------------
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JavaTimeModule())
                .setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //.registerModule(new ParameterNamesModule())
        //.registerModule(new Jdk8Module())
    }

    /**
     * java对象转换为json字符串.
     *
     * @param object target object
     * @return json format String
     */
    private String bean2Json(Object object) {
        if (object instanceof String) {
            return object.toString();
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("json processing exception", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * json字符串转换为指定类型java对象.
     *
     * @param json 需要转换的json字符串
     * @param type 转换后的java类型
     * @return target class of type
     */
    private <T> T json2Bean(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            log.error("json processing exception", e);
            throw new RuntimeException(e);
        }
    }
}
