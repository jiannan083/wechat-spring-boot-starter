package cn.bfay.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WechatPageAccessToken.
 *
 * @author wangjiannan
 */
public class WechatPageAccessToken extends WechatAccessToken {
    private static final long serialVersionUID = 2325228139959780131L;

    /**
     * refresh_token.
     */
    @JsonProperty("refresh_token")
    private String refreshToken;

    /**
     * openid.
     */
    @JsonProperty("openid")
    private String openid;

    /**
     * scope.
     */
    @JsonProperty("scope")
    private String scope;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "WechatPageAccessToken{" +
                "refreshToken='" + refreshToken + '\'' +
                ", openid='" + openid + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}