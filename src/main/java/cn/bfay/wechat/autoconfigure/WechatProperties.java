package cn.bfay.wechat;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * WechatProperties.
 *
 * @author wangjiannan
 * @since 2019/10/24
 */
@ConfigurationProperties(prefix = "bfay.wechat")
public class WechatProperties {
    /**
     * secret.
     */
    private String secret;
    /**
     * token.
     */
    private String token;
    /**
     * appid.
     */
    private String appid;
    /**
     * 是否开启debug模式；true-开启；false-不开启（默认）.
     */
    private boolean debug = false;

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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public String toString() {
        return "WechatProperties{" +
                "secret='" + secret + '\'' +
                ", token='" + token + '\'' +
                ", appid='" + appid + '\'' +
                ", debug=" + debug +
                '}';
    }
}
