package cn.wangjiannan.wechat.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * WechatProperties.
 *
 * @author wangjiannan
 */
@ConfigurationProperties(prefix = WechatProperties.WECHAT_PREFIX)
public class WechatProperties {
    public static final String WECHAT_PREFIX = "wechat";

    private String appid;
    private String secret;
    private String token;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
