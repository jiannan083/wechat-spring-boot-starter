package cn.bfay.wechat;

import cn.bfay.commons.okhttp.OkHttpUtils;
import cn.bfay.wechat.model.WechatUser;

/**
 * 用户管理.
 *
 * @author wangjiannan
 */
public class WechatUserUtils {
    /**
     * 简体.
     */
    public static final String LANG_ZH_CN = "zh_CN";
    /**
     * 繁体
     */
    public static final String LANG_ZH_TW = "zh_TW";
    /**
     * 英语
     */
    public static final String LANG_EN = "en";

    /**
     * 根据全局access_token获取用户信息,默认中文.
     *
     * @param accessToken access_token
     * @param openid      openid
     * @return {@link WechatUser}
     */
    public static WechatUser getUserInfo(String accessToken, String openid) {
        return getUserInfo(accessToken, openid, LANG_ZH_CN);
    }

    /**
     * 根据全局access_token获取用户信息.
     *
     * @param accessToken access_token
     * @param openid      openid
     * @param lang        语言
     * @return {@link WechatUser}
     */
    public static WechatUser getUserInfo(String accessToken, String openid, String lang) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=%s", accessToken, openid, lang);
        return OkHttpUtils.executeGet(url, WechatUser.class);
    }
}
