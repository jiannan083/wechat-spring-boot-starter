package test;

import cn.bfay.wechat.WechatBean;
import cn.bfay.wechat.WechatUtils;
import cn.bfay.wechat.model.menu.Menu;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WechatTest.
 *
 * @author wangjiannan
 */
public class WechatTest {
    private static final Logger log = LoggerFactory.getLogger(WechatTest.class);

    @Test
    public void testWechat() {
        WechatUtils wechatUtils = new WechatUtils("", "", "");
        WechatBean wechatBean = wechatUtils.processAccessToken();
        System.out.println("-----" + wechatBean.toString());
        //
        try {
            wechatUtils.createMenu(wechatBean.getAccessToken(), new Menu());
        } catch (Exception e) {
            log.error("-----", e);
        }
        //
        String jsApiTicket = wechatUtils.getJSApiTicket(wechatBean.getAccessToken());
        System.out.println("-----" + jsApiTicket);
    }
}
