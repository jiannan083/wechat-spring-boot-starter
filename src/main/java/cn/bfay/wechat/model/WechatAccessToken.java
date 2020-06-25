package cn.bfay.lion.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * WechatToken.
 *
 * @author wangjiannan
 */
@Data
public class WechatAccessToken {
    /**
     * accessToken.
     */
    @JsonProperty("access_token")
    private String accessToken;
    /**
     * accessToken生存时间.
     */
    @JsonProperty("expires_in")
    private Long expiresIn;
}