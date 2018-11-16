package cn.bfay.wechat;

/**
 * WechatBean.
 *
 * @author wangjiannan
 */
public class WechatBean {
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
        return "WechatBean{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}