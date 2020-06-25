package cn.bfay.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * WechatToken.
 *
 * @author wangjiannan
 */
public class WechatAccessToken implements Serializable {
    private static final long serialVersionUID = 4648219438889900189L;

    /**
     * access_token.
     */
    @JsonProperty("access_token")
    private String accessToken;
    /**
     * accessToken生存时间.
     */
    @JsonProperty("expires_in")
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "WechatAccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}