package cn.bfay.wechat.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

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
     * 模板map.
     */
    private Map<String, String> template = new HashMap<>();

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

    public Map<String, String> getTemplate() {
        return template;
    }

    public void setTemplate(Map<String, String> template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "WechatProperties{" +
                "secret='" + secret + '\'' +
                ", token='" + token + '\'' +
                ", appid='" + appid + '\'' +
                ", template=" + template +
                '}';
    }
}
