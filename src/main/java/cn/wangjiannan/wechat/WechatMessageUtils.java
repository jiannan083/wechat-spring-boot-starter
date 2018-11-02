package cn.wangjiannan.wechat;

import cn.wangjiannan.wechat.model.Article;
import cn.wangjiannan.wechat.model.RequestMessage;
import cn.wangjiannan.wechat.model.ResponseNewsMessage;
import cn.wangjiannan.wechat.model.ResponseTextMessage;
import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息工具类.
 *
 * @author wangjiannan
 */
@Slf4j
public class WechatMessageUtils {
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
    public RequestMessage parseRequestXml(HttpServletRequest request) {
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
            return JSON.parseObject(JSON.toJSONString(map), RequestMessage.class);
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
        return parseTextMessageToXml(responseTextMessage);
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
        return parseNewsMessageToXml(responseNewsMessage);
    }

    /**
     * 文本消息对象转换成xml.
     *
     * @param responseTextMessage .
     * @return string
     */
    private String parseTextMessageToXml(ResponseTextMessage responseTextMessage) {
        xstream.alias("xml", ResponseTextMessage.class);
        return xstream.toXML(responseTextMessage);
    }

    /**
     * 图文消息对象转换成xml.
     *
     * @param responseNewsMessage .
     * @return string
     */
    private String parseNewsMessageToXml(ResponseNewsMessage responseNewsMessage) {
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


    ///**
    // * 处理message信息.
    // *
    // * @param request request
    // * @return string
    // */
    //public String processMessage(HttpServletRequest request) {
    //    // xml请求解析
    //    Map<String, String> requestMap = parseXml(request);
    //    logger.info("接收到请求内容={}", requestMap.toString());
    //    // 发送方帐号（open_id）
    //    String fromUserName = requestMap.get("FromUserName");
    //    // 公众帐号
    //    String toUserName = requestMap.get("ToUserName");
    //    // 消息类型
    //    String msgType = requestMap.get("MsgType");
    //    switch (msgType) {
    //        case REQ_MESSAGE_TYPE_EVENT: //a.事件推送
    //            String eventType = requestMap.get("Event");
    //            switch (eventType) {
    //                case EVENT_TYPE_SUBSCRIBE: // 订阅
    //                    //"谢谢您的关注！回复\"?\"获取帮助";
    //                    return responseTextMessage(fromUserName, toUserName, "谢谢您的关注~");
    //                case EVENT_TYPE_UNSUBSCRIBE: // 取消订阅
    //                    logger.info("取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息");
    //                default:
    //                    System.out.println();
    //            }
    //        case REQ_MESSAGE_TYPE_TEXT: // b.文本
    //            String content = StringUtils.trimAllWhitespace(requestMap.get("Content"));
    //            if ("?".equals(content) || "？".equals(content)) {
    //                StringBuffer buffer = new StringBuffer();
    //                buffer.append("您好，我是我，请回复数字选择服务：").append("\n\n");
    //                buffer.append("1  天气预报").append("\n");
    //                buffer.append("<a href=\"https://www.baidu.com/\">百度</a>").append("\n\n");
    //                buffer.append("回复“?”显示此帮助菜单:8未开放");
    //                return responseTextMessage(fromUserName, toUserName, buffer.toString());
    //            } else {
    //                return responseTextMessage(fromUserName, toUserName, "无效请求编号!");
    //            }
    //        case REQ_MESSAGE_TYPE_LINK: //链接
    //            String title = requestMap.get("Title"); //消息标题
    //            String description = requestMap.get("Description"); //消息描述
    //            String url = requestMap.get("Url"); //消息链接
    //            //String msgid = requestMap.get("MsgId"); //消息id，64位整型
    //
    //        default:
    //            return responseTextMessage(fromUserName, toUserName, "无效请求!");
    //    }
    //}

    ///**
    // * 获取jsApiTicket.
    // *
    // * @param accessToken accessToken
    // * @return string
    // */
    //public String getJSApiTicket(String accessToken) {
    //    HttpClientUtils hc = new HttpClientUtils("https://api.weixin.qq.com/cgi-bin/ticket/getticket");
    //    hc.setParams("access_token", accessToken);
    //    hc.setParams("type", "jsapi");
    //    String result = hc.doGet();
    //    logger.info("获取微信jsApiTicket方法http请求;result={}", result);
    //    if (StringUtils.isEmpty(result)) {
    //        logger.error("获取微信jsApiTicket方法http请求失败");
    //        return null;
    //    }
    //    String jsApiTicket = JSON.parseObject(result).getString("ticket");
    //    if (StringUtils.isEmpty(jsApiTicket)) {
    //        logger.error("获取微信jsApiTicket方法htttp请求成功,微信服务端返回错误信息");
    //        return null;
    //    }
    //    return jsApiTicket;
    //}

    ///**
    // * 根据全局access_token获取用户信息
    // *
    // * @param fromUserName
    // * @throws Exception
    // * @author wangjiannan
    // * @date 2016年6月27日 下午3:37:26
    // */
    //private static void getUserInfo(String fromUserName) throws Exception {
    //    String url = "".replace("replaceAccessToken", "").replace("replaceOpenid", fromUserName).replace("replaceLang", "zh_CN");
    //    // String url = ConfigProperties.WEIXIN_GET_USER_INFO_URL.replace("replaceAccessToken", ConfigProperties.WEIXIN_ACCESS_TOKEN)
    //    // .replace("replaceOpenid", fromUserName).replace("replaceLang", "zh_CN");
    //    HttpClientUtils hc = new HttpClientUtils(url);
    //    String result = hc.doGet();
    //
    //    //User user = JSONObject.parseObject(result, User.class);
    //    //
    //    //logger.info("----------------------------user={}", user);
    //    //userRepository.save(user);
    //    // logger.info("*****************************************;getUserInfoResult={}", result);
    //}

    ///**
    // * 单图文消息-含图片.
    // *
    // * @param fromUserName fromUserName
    // * @param toUserName   toUserName
    // * @return string
    // */
    //private String responseSingleNewsMessageIncludePicture(String fromUserName, String toUserName) {
    //    List<Article> articleList = new ArrayList<>();
    //    Article article = new Article();
    //    article.setTitle("微信开发java版");
    //    article.setDescription("王健楠，90后，微信公众帐号开发经验1周。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
    //    article.setPicUrl("http://uploads.pincai.com/image/20151030/1446197440137323.png");
    //    article.setUrl("https://www.baidu.com/");
    //    articleList.add(article);
    //    return responseNewsMessage(fromUserName, toUserName, articleList);
    //}
    //
    ///**
    // * 单图文消息-不含图片.
    // *
    // * @param fromUserName fromUserName
    // * @param toUserName   toUserName
    // * @return string
    // */
    //private String responseSingleNewsMessageExcludePicture(String fromUserName, String toUserName) {
    //    List<Article> articleList = new ArrayList<>();
    //    Article article = new Article();
    //    article.setTitle("微信开发java版");
    //    article.setDescription("王健楠，90后，微信公众帐号开发经验1周。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
    //    article.setPicUrl("");
    //    article.setUrl("https://www.baidu.com/");
    //    articleList.add(article);
    //    return responseNewsMessage(fromUserName, toUserName, articleList);
    //}
    //
    ///**
    // * 多图文消息.
    // *
    // * @param fromUserName fromUserName
    // * @param toUserName   toUserName
    // * @return string
    // */
    //private String responseMuchNewsMessage(String fromUserName, String toUserName) {
    //    List<Article> articleList = new ArrayList<>();
    //    Article article1 = new Article();
    //    article1.setTitle("微信开发java版");
    //    article1.setDescription("王健楠，90后，微信公众帐号开发经验1周。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
    //    article1.setPicUrl("http://uploads.pincai.com/image/20151030/1446197440137323.png");
    //    article1.setUrl("https://www.baidu.com/");
    //
    //    Article article2 = new Article();
    //    article2.setTitle("微信开发java版");
    //    article2.setDescription("王健楠，90后，微信公众帐号开发经验1周。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
    //    article2.setPicUrl("http://uploads.pincai.com/image/20151030/1446197440137323.png");
    //    article2.setUrl("https://www.baidu.com/");
    //
    //    Article article3 = new Article();
    //    article3.setTitle("微信开发java版");
    //    article3.setDescription("王健楠，90后，微信公众帐号开发经验1周。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
    //    article3.setPicUrl("http://uploads.pincai.com/image/20151030/1446197440137323.png");
    //    article3.setUrl("https://www.baidu.com/");
    //
    //    articleList.add(article1);
    //    articleList.add(article2);
    //    articleList.add(article3);
    //    return responseNewsMessage(fromUserName, toUserName, articleList);
    //}
    //
    ///**
    // * 多图文消息-首个不含图片.
    // *
    // * @param fromUserName fromUserName
    // * @param toUserName   toUserName
    // * @return string
    // */
    //private String responseMuchNewsMessageFirstExcludePicture(String fromUserName, String toUserName) {
    //    List<Article> articleList = new ArrayList<>();
    //    Article article1 = new Article();
    //    article1.setTitle("微信开发java版");
    //    article1.setDescription("王健楠，90后，微信公众帐号开发经验1周。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
    //    article1.setPicUrl("");
    //    article1.setUrl("https://www.baidu.com/");
    //
    //    Article article2 = new Article();
    //    article2.setTitle("微信开发java版");
    //    article2.setDescription("王健楠，90后，微信公众帐号开发经验1周。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
    //    article2.setPicUrl("http://uploads.pincai.com/image/20151030/1446197440137323.png");
    //    article2.setUrl("https://www.baidu.com/");
    //
    //    Article article3 = new Article();
    //    article3.setTitle("微信开发java版");
    //    article3.setDescription("王健楠，90后，微信公众帐号开发经验1周。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
    //    article3.setPicUrl("http://uploads.pincai.com/image/20151030/1446197440137323.png");
    //    article3.setUrl("https://www.baidu.com/");
    //
    //    articleList.add(article1);
    //    articleList.add(article2);
    //    articleList.add(article3);
    //    return responseNewsMessage(fromUserName, toUserName, articleList);
    //}
    //
    ///**
    // * 多图文消息-末个不含图片.
    // *
    // * @param fromUserName fromUserName
    // * @param toUserName   toUserName
    // * @return string
    // */
    //private String responseMuchNewsMessageLastExcludePicture(String fromUserName, String toUserName) {
    //    List<Article> articleList = new ArrayList<>();
    //    Article article1 = new Article();
    //    article1.setTitle("微信开发java版");
    //    article1.setDescription("王健楠，90后，微信公众帐号开发经验1周。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
    //    article1.setPicUrl("http://uploads.pincai.com/image/20151030/1446197440137323.png");
    //    article1.setUrl("https://www.baidu.com/");
    //
    //    Article article2 = new Article();
    //    article2.setTitle("微信开发java版");
    //    article2.setDescription("王健楠，90后，微信公众帐号开发经验1周。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
    //    article2.setPicUrl("http://uploads.pincai.com/image/20151030/1446197440137323.png");
    //    article2.setUrl("https://www.baidu.com/");
    //
    //    Article article3 = new Article();
    //    article3.setTitle("微信开发java版");
    //    article3.setDescription("王健楠，90后，微信公众帐号开发经验1周。为帮助初学者入门，特推出此系列教程，也希望借此机会认识更多同行！");
    //    article3.setPicUrl("");
    //    article3.setUrl("https://www.baidu.com/");
    //
    //    articleList.add(article1);
    //    articleList.add(article2);
    //    articleList.add(article3);
    //    return responseNewsMessage(fromUserName, toUserName, articleList);
    //}

    // /**
    // * 音乐消息对象转换成xml
    // *
    // * @param musicMessage
    // * 音乐消息对象
    // * @return xml
    // */
    // public static String musicMessageToXml(MusicMessage musicMessage) {
    // xstream.alias("xml", musicMessage.getClass());
    // return xstream.toXML(musicMessage);
    // }
}
