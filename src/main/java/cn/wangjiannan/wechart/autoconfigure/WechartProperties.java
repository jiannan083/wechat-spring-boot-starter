package cn.wangjiannan.wechart.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * WechartProperties.
 *
 * @author wangjiannan
 */
@ConfigurationProperties(prefix = WechartProperties.WECHART_PREFIX)
public class WechartProperties {
    public static final String WECHART_PREFIX = "wechart";

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
