package test;

import cn.bfay.wechat.WechatCoreManager;
import cn.bfay.wechat.WechatUserUtils;
import cn.bfay.wechat.WechatUtils;
import cn.bfay.wechat.model.WechatToken;
import cn.bfay.wechat.model.WechatUser;
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
    public void testWechatToken() {
        WechatCoreManager wechatCoreManager = new WechatCoreManager("", "", "");
        WechatToken wechatToken = wechatCoreManager.processAccessToken();
        System.out.println("-----" + wechatToken.toString());
    }

    @Test
    public void testCreateMenu() {
        WechatUtils.createMenu("", new Menu());
    }

    @Test
    public void testJSApiTicket() {
        String jsApiTicket = WechatUtils.getJSApiTicket("");
        System.out.println("-----" + jsApiTicket);
    }

    @Test
    public void testGetUserInfo() {
        WechatUser wechatUser = WechatUserUtils.getUserInfo("", "");
    }
}
