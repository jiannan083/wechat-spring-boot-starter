package cn.bfay.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * WechatJsapiTicket.
 *
 * @author wangjiannan
 */
public class WechatJsapiTicket implements Serializable {
    private static final long serialVersionUID = -3274744688770915027L;

    /**
     * errcode.
     */
    private Integer errcode;
    /**
     * errmsg.
     */
    private String errmsg;
    /**
     * ticket.
     */
    private String ticket;
    /**
     * expires_in.
     */
    @JsonProperty("expires_in")
    private Long expiresIn;
    //{
    //    "errcode":0,
    //    "errmsg":"ok",
    //    "ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA",
    //    "expires_in":7200
    //}

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "WechatJsapiTicket{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", ticket='" + ticket + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
