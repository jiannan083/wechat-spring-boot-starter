package cn.bfay.wechat.model;

/**
 * WechatToken.
 *
 * @author wangjiannan
 */
public class WechatToken {
    /**
     * accessToken.
     */
    private String accessToken;
    /**
     * accessToken生存时间.
     */
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
        return "WechatToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}