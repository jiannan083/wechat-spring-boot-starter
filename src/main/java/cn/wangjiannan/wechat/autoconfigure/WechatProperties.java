package cn.wangjiannan.wechat.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * WechatProperties.
 *
 * @author wangjiannan
 */
@Data
@ConfigurationProperties(prefix = WechatProperties.WECHAT_PREFIX)
public class WechatProperties {
    public static final String WECHAT_PREFIX = "wechat";

    private String appid;
    private String secret;
    private String token;

}
