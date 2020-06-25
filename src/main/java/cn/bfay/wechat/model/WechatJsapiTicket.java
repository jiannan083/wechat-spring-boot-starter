package cn.bfay.lion.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * WechatJsapiTicket.
 *
 * @author wangjiannan
 */
@Data
public class WechatJsapiTicket {
    private Integer errcode;
    private String errmsg;
    private String ticket;
    @JsonProperty("expires_in")
    private Long expiresIn;
    //{
    //    "errcode":0,
    //    "errmsg":"ok",
    //    "ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA",
    //    "expires_in":7200
    //}
}
