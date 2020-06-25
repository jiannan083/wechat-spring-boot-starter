package cn.bfay.lion.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * WechatPageAccessToken.
 *
 * @author wangjiannan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WechatPageAccessToken extends WechatAccessToken {
    @JsonProperty("refresh_token")
    private String refreshToken;

    //@JsonProperty("openid")
    private String openid;

    //@JsonProperty("scope")
    private String scope;
}