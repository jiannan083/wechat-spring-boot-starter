package test;

import cn.bfay.wechat.WechatCoreManager;
import cn.bfay.wechat.WechatUtils;
import cn.bfay.wechat.model.WechatToken;
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
        WechatCoreManager wechatCoreManager = new WechatCoreManager("", "", "");
        WechatToken wechatToken = wechatCoreManager.processAccessToken();
        System.out.println("-----" + wechatToken.toString());
        //
        try {
            WechatUtils.createMenu(wechatToken.getAccessToken(), new Menu());
        } catch (Exception e) {
            log.error("-----", e);
        }
        //
        String jsApiTicket = WechatUtils.getJSApiTicket(wechatToken.getAccessToken());
        System.out.println("-----" + jsApiTicket);
    }
}
