package test;

import cn.bfay.wechat.WechatCoreManager;
import cn.bfay.wechat.WechatTemplateMessageUtils;
import cn.bfay.wechat.WechatUserUtils;
import cn.bfay.wechat.WechatUtils;
import cn.bfay.wechat.model.TemplateMessage;
import cn.bfay.wechat.model.WechatToken;
import cn.bfay.wechat.model.WechatUser;
import cn.bfay.wechat.model.menu.Menu;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * WechatTest.
 *
 * @author wangjiannan
 */
public class WechatTest {
    private static final Logger log = LoggerFactory.getLogger(WechatTest.class);

    private static String appid = "";
    private static String secret = "";
    private static String token = "";
    private static String accessToken = "";

    private static String openid = "";

    @Test
    public void testWechatToken() {
        WechatCoreManager wechatCoreManager = new WechatCoreManager(appid, secret, token);
        WechatToken wechatToken = wechatCoreManager.processAccessToken();
        System.out.println("-----" + wechatToken.toString());
    }

    @Test
    public void testCreateMenu() {
        WechatUtils.createMenu(accessToken, new Menu());
    }

    @Test
    public void testJSApiTicket() {
        String jsApiTicket = WechatUtils.getJSApiTicket(accessToken);
        System.out.println("-----" + jsApiTicket);
    }

    @Test
    public void testGetUserInfo() {
        WechatUser wechatUser = WechatUserUtils.getUserInfo(accessToken, openid);
    }

    @Test
    public void testTemplateMessage() {
        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTouser(openid);
        templateMessage.setTemplateId("2LUnK_0RWUkgzCgaZrPzNslF7dKZOZRihgf-Lge8rb8");
        templateMessage.setUrl("www.baidu.com");
        String content = String.format(WechatTemplateMessageUtils.TEMPLATE_DATA1, "hello", "word1", "word2", "word3", "remark");
        templateMessage.setData(content);
        WechatTemplateMessageUtils.sendTemplateMessage(accessToken, templateMessage);
    }

    @Test
    public void testWechatIp() {
        List<String> ips = WechatUtils.getCallbackIp(accessToken);
        ips.forEach(System.out::println);
    }
}
