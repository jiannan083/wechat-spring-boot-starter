package cn.bfay.lion.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 微信服务器IP地址.
 *
 * @author wangjiannan
 */
@Data
public class WechatServerIp implements Serializable {
    private static final long serialVersionUID = -887765919717061762L;

    @JsonProperty("ip_list")
    private List<String> ips;
}
