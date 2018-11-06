package cn.wangjiannan.wechat;

import cn.wangjiannan.util.HttpClientUtils;
import cn.wangjiannan.wechat.model.menu.Menu;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 微信工具类.
 *
 * @author wangjiannan
 */
public class WechatUtils {
    private static final Logger log = LoggerFactory.getLogger(WechatUtils.class);
    private final String appid;
    private final String secret;
    private final String token;

    public WechatUtils(String appid, String secret, String token) {
        this.appid = appid;
        this.secret = secret;
        this.token = token;
    }

    /**
     * 获取access_token.
     *
     * @return {@link WechatBean}
     */
    public WechatBean processAccessToken() {
        //try {
        log.info("获取微信accessToken-start");
        HttpClientUtils hc = new HttpClientUtils("https://api.weixin.qq.com/cgi-bin/token");
        hc.setParams("grant_type", "client_credential");
        hc.setParams("appid", appid);
        hc.setParams("secret", secret);
        String result = hc.doGet();
        log.info("获取微信accessToken方法http请求;result={}", result);
        JSONObject json = JSON.parseObject(result);
        String accessToken = json.getString("access_token");
        if (!StringUtils.hasText(accessToken)) {
            throw new RuntimeException("access_token is null");
        }
        Long expiresIn = json.getLong("expires_in");
        if (expiresIn == null) {
            throw new RuntimeException("expires_in is null");
        }
        WechatBean wechatBean = new WechatBean();
        log.info("获取微信accessToken-end");
        wechatBean.setAccessToken(accessToken);
        wechatBean.setExpiresIn(expiresIn);
        return wechatBean;
    }

    /**
     * 微信服务验证.
     *
     * @param request  req
     * @param response rep
     */
    public void getCore(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (checkSignature(request.getParameter("signature"),
                    request.getParameter("timestamp"),
                    request.getParameter("nonce"))) {
                out.print(request.getParameter("echostr"));
            }
        } catch (Exception e) {
            log.error("wechat get core error", e);
            throw new RuntimeException("wechat get core error");
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 微信服务核心.
     *
     * @param request     req
     * @param response    rep
     * @param respMessage 调用核心业务类接收消息、处理消息
     */
    public void postCore(HttpServletRequest request, HttpServletResponse response, String respMessage) {
        PrintWriter out = null;
        try {
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            // 调用核心业务类接收消息、处理消息
            //String respMessage = wechatMessageService.processRequest(request);
            // 响应消息
            out = response.getWriter();
            out.print(respMessage);
        } catch (Exception e) {
            log.error("wechat post core error", e);
            throw new RuntimeException("wechat post core error");
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    // 验证签名-检验signature对请求进行校验
    private boolean checkSignature(String signature, String timestamp, String nonce) throws NoSuchAlgorithmException {
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

    /**
     * 创建菜单.
     *
     * @param accessToken accessToken
     * @param menu        {@link Menu}
     */
    public void createMenu(String accessToken, Menu menu) {
        // https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN//post请求post
        HttpClientUtils hc = new HttpClientUtils(String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s", accessToken));
        String result = hc.doPost(JSON.toJSONString(menu));
        log.info("创建菜单返回结果,result={}", result);
        if (StringUtils.isEmpty(result) || !"0".equalsIgnoreCase(JSON.parseObject(result).getString("errcode"))) {
            throw new RuntimeException("创建菜单失败!");
        }
    }

    /**
     * 获取微信jsApiTicket.
     *
     * @param accessToken accessToken
     * @return string
     */
    public String getJSApiTicket(String accessToken) {
        //https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=replaceAccessToken&type=jsapi//get请求
        HttpClientUtils hc = new HttpClientUtils(String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi", accessToken));
        String result = hc.doGet();
        log.info("获取微信jsApiTicket方法http请求;result={}", result);
        if (StringUtils.isEmpty(result)) {
            throw new RuntimeException("获取微信jsApiTicket失败,result=" + result);
        }
        String jsApiTicket = JSON.parseObject(result).getString("ticket");
        if (StringUtils.isEmpty(jsApiTicket)) {
            throw new RuntimeException("获取微信jsApiTicket失败,result=" + result);
        }
        return jsApiTicket;
    }

    ///**
    // * JS签名
    // *
    // * @param url
    // * @return
    // * @author wangjiannan
    // * @date 2017年6月20日 下午2:02:19
    // */
    //public static Map<String, String> getJsSign(String url) {
    //    String nonce_str = UUID.randomUUID().toString();
    //    String timestamp = Long.toString(System.currentTimeMillis() / 1000);
    //    String signature = "";
    //    // 注意这里参数名必须全部小写，且必须有序
    //    String string1 = "jsapi_ticket=" + WechatProperties.JSAPI_TICKET + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
    //    try {
    //        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
    //        crypt.reset();
    //        crypt.update(string1.getBytes("UTF-8"));
    //        signature = byteToHex(crypt.digest());
    //    } catch (Exception e) {
    //        logger.error("", e);
    //    }
    //    Map<String, String> ret = new HashMap<String, String>();
    //    ret.put("url", url);
    //    ret.put("jsapi_ticket", WechatProperties.JSAPI_TICKET);
    //    ret.put("nonceStr", nonce_str);
    //    ret.put("timestamp", timestamp);
    //    ret.put("signature", signature);
    //    ret.put("appId", WechatProperties.APPID);
    //    return ret;
    //}

    //// ----page----
    ///**
    // * 网页授权
    // *
    // * @param code
    // * @author wangjiannan
    // * @date 2017年6月20日 下午6:12:25
    // */
    //public static void getPageAuthorization(String code) {
    //    HttpClientUtils hc = new HttpClientUtils(WechatProperties.PAGE_ACCESS_TOKEN_URL);
    //    hc.setParams("appid", WechatProperties.APPID);
    //    hc.setParams("secret", WechatProperties.SECRET);
    //    hc.setParams("code", code);
    //    hc.setParams("grant_type", "authorization_code");
    //    String result = hc.doPost();
    //    logger.info("*****************************************;pageTokenResult={}", result);
    //    String pageToken = JSON.parseObject(result).getString("access_token");
    //    // String expiresIn = JSON.parseObject(result).getString("expires_in");
    //    // String refreshToken = JSON.parseObject(result).getString("refresh_token");
    //    String openid = JSON.parseObject(result).getString("openid");
    //    // String scope = JSON.parseObject(result).getString("scope");
    //    // logger.info("*****************************************;accessToken={}", accessToken);
    //    // logger.info("*****************************************;expiresIn={}", expiresIn);
    //    // logger.info("*****************************************;refreshToken={}", refreshToken);
    //    // logger.info("*****************************************;openid={}", openid);
    //    // logger.info("*****************************************;scope={}", scope);
    //    // 3.刷新access_token（如果需要）
    //    // boolean isValid = checkPageTokenIsValid(accessToken, openid);
    //    // if (!isValid) {
    //    // // 刷新access_token
    //    // String refreshTokenUrl = Url.WEIXIN_REFRESH_TOKEN_URL.replace("replaceAppid", SecretConstants.APPID).replace("replaceRefreshToken", refreshToken);
    //    // HttpClientUtils hc1 = new HttpClientUtils(refreshTokenUrl);
    //    // String result1 = hc1.doPost();
    //    // accessToken = JSON.parseObject(result1).getString("access_token");
    //    // // expiresIn = JSON.parseObject(result1).getString("expires_in");
    //    // refreshToken = JSON.parseObject(result1).getString("refresh_token");
    //    // openid = JSON.parseObject(result1).getString("openid");
    //    // // scope = JSON.parseObject(result1).getString("scope");
    //    // }
    //    // 4.拉取用户信息(需scope为 snsapi_userinfo)
    //    // User weixinUserInfo = getUserInfo(pageToken, openid);
    //
    //    HttpClientUtils hc1 = new HttpClientUtils(WechatProperties.PAGE_GET_USER_INFO_URL + "?access_token=" + pageToken + "&openid=" + openid + "&lang=zh_CN");
    //    String result1 = hc1.doGet();
    //    logger.info("*****************************************;result1={}", result1);
    //    User weixinUserInfo = JSONObject.parseObject(result, User.class);
    //    logger.info("*****************************************;用户信息={}", weixinUserInfo);
    //}
    //
    ///**
    // * 检验授权凭证（access_token）是否有效
    // *
    // * @param accessToken
    // * @param openid
    // * @return
    // * @throws Exception
    // * @author wangjiannan
    // * @date 2016年6月27日 上午11:24:05
    // */
    //@Deprecated
    //private static boolean checkPageTokenIsValid(String accessToken, String openid) throws Exception {
    //    // String pageTokenIsValidUrl = ConfigProperties.WEIXIN_CHECK_PAGE_TOKEN_IS_VALID_URL.replace("replaceAccessToken",
    //    // accessToken).replace("replaceOpenid",
    //    // openid);
    //    // HttpClientUtils hc = new HttpClientUtils(pageTokenIsValidUrl);
    //    // String result = hc.doGet();
    //    // logger.info("*****************************************;pageTokenIsValidResult={}", result);
    //    // if ("0".equals(JSON.parseObject(result).getString("errcode"))) {
    //    // logger.info("*****************************************;pageTokenIsValid未过期");
    //    // return true;
    //    // }
    //    return false;
    //}

//    #通过access_token获取用户基本信息地址
//#get_user_info_url=https://api.weixin.qq.com/cgi-bin/user/info?access_token=replaceAccessToken&openid=replaceOpenid&lang=replaceLang
//            #微信授权地址//https://open.weixin.qq.com/connect/oauth2/authorize?appid=replaceAppid&redirect_uri=replaceUrl&response_type=code&scope=replaceScope&state=STATE#wechat_redirect
//            authorization_url=https://open.weixin.qq.com/connect/oauth2/authorize
//    redirect_uri=www.wangjiannan.cn
//#https://api.weixin.qq.com/sns/oauth2/access_token?appid=replaceAppid&secret=replaceSecret&code=replaceCode&grant_type=authorization_code
//    page_access_token_url=https://api.weixin.qq.com/sns/oauth2/access_token
//            #check_page_token_is_valid_url=https://api.weixin.qq.com/sns/auth?access_token=replaceAccessToken&openid=replaceOpenid
//            #refresh_token_url=https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=replaceAppid&grant_type=refresh_token&refresh_token=replaceRefreshToken
//    page_get_user_info_url=https://api.weixin.qq.com/sns/userinfo

    ///**
    // * 根据全局access_token获取用户信息
    // *
    // * @param fromUserName
    // * @throws Exception
    // * @author wangjiannan
    // * @date 2016年6月27日 下午3:37:26
    // */
    //private void getUserInfo(String fromUserName) throws Exception {
    //    String url = "".replace("replaceAccessToken", "").replace("replaceOpenid", fromUserName).replace("replaceLang", "zh_CN");
    //    // String url = ConfigProperties.WEIXIN_GET_USER_INFO_URL.replace("replaceAccessToken", ConfigProperties.WEIXIN_ACCESS_TOKEN)
    //    // .replace("replaceOpenid", fromUserName).replace("replaceLang", "zh_CN");
    //    HttpClientUtils hc = new HttpClientUtils(url);
    //    String result = hc.doGet();
    //
    //    User user = JSONObject.parseObject(result, User.class);
    //
    //    logger.info("----------------------------user={}", user);
    //    userRepository.save(user);
    //    // logger.info("*****************************************;getUserInfoResult={}", result);
    //}

    //private static String byteToHex(byte[] hash) {
    //    Formatter formatter = new Formatter();
    //    for (byte b : hash) {
    //        formatter.format("%02x", b);
    //    }
    //    String result = formatter.toString();
    //    formatter.close();
    //    return result;
    //}
}
