package cn.bfay.wechat;

import cn.bfay.commons.okhttp.OkHttpUtils;
import cn.bfay.wechat.model.menu.Menu;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 微信工具类.
 *
 * @author wangjiannan
 */
public class WechatUtils {
    private static final Logger log = LoggerFactory.getLogger(WechatUtils.class);

    /**
     * 创建菜单.
     *
     * @param accessToken accessToken
     * @param menu        {@link Menu}
     */
    public static void createMenu(String accessToken, Menu menu) {
        String menuContent = JSON.toJSONString(menu);
        // https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN//post请求post
        String result = OkHttpUtils.executePost(String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s", accessToken), menuContent, String.class);
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
    public static String getJSApiTicket(String accessToken) {
        //https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=replaceAccessToken&type=jsapi//get请求
        String url = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi", accessToken);
        String result = OkHttpUtils.executeGet(url, String.class);
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
//#get_user_info_url=
//            #微信授权地址//https://open.weixin.qq.com/connect/oauth2/authorize?appid=replaceAppid&redirect_uri=replaceUrl&response_type=code&scope=replaceScope&state=STATE#wechat_redirect
//            authorization_url=https://open.weixin.qq.com/connect/oauth2/authorize
//    redirect_uri=www.wangjiannan.cn
//#https://api.weixin.qq.com/sns/oauth2/access_token?appid=replaceAppid&secret=replaceSecret&code=replaceCode&grant_type=authorization_code
//    page_access_token_url=https://api.weixin.qq.com/sns/oauth2/access_token
//            #check_page_token_is_valid_url=https://api.weixin.qq.com/sns/auth?access_token=replaceAccessToken&openid=replaceOpenid
//            #refresh_token_url=https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=replaceAppid&grant_type=refresh_token&refresh_token=replaceRefreshToken
//    page_get_user_info_url=https://api.weixin.qq.com/sns/userinfo


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
