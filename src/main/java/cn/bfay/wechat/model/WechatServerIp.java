package cn.bfay.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 微信服务器IP地址.
 *
 * @author wangjiannan
 */
public class WechatServerIp implements Serializable {
    private static final long serialVersionUID = -887765919717061762L;
    @JsonProperty("ip_list")
    private List<String> ips;

    public List<String> getIps() {
        return ips;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
    }
}
