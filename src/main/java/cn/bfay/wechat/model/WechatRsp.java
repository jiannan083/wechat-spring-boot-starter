package cn.bfay.wechat.model;

import java.io.Serializable;

/**
 * WechatRsp.
 *
 * @author wangjiannan
 */
public class WechatRsp implements Serializable {
    private static final long serialVersionUID = -5144691922119141686L;

    /**
     * code码.
     */
    private Integer errcode;
    /**
     * 错误信息.
     */
    private String errmsg;

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

    @Override
    public String toString() {
        return "WechatRsp{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
