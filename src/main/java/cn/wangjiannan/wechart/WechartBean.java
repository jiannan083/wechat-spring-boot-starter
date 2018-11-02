package cn.wangjiannan.wechart;

import lombok.Data;

/**
 * WechartBean.
 *
 * @author wangjiannan
 */
@Data
public class WechartBean {
    /**
     * accessToken.
     */
    private String accessToken;
    /**
     * accessToken生存时间.
     */
    private Long expiresIn;

}