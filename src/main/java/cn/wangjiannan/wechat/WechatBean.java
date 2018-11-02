package cn.wangjiannan.wechat;

import lombok.Data;

/**
 * WechatBean.
 *
 * @author wangjiannan
 */
@Data
public class WechatBean {
    /**
     * accessToken.
     */
    private String accessToken;
    /**
     * accessToken生存时间.
     */
    private Long expiresIn;

}