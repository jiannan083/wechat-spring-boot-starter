package test;

import cn.bfay.wechat.WechatCoreUtil;
import cn.bfay.wechat.model.WechatAccessToken;
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

    @Test
    public void testWechatToken() {
        WechatCoreUtil wechatCoreUtil = new WechatCoreUtil(appid, secret, token);

        WechatAccessToken wechatAccessToken = wechatCoreUtil.getAccessToken();
        System.out.println("-----" + wechatAccessToken.toString());

        String accessToken = wechatAccessToken.getAccessToken();
        wechatCoreUtil.createMenu(accessToken, new Menu());

        wechatCoreUtil.getUserInfo(accessToken, "");

        List<String> ips = wechatCoreUtil.getWechatServerIp(accessToken);
        ips.forEach(System.out::println);
    }

    //
    //@Test
    //public void testTemplateMessage() {
    //    TemplateMessage templateMessage = new TemplateMessage();
    //    templateMessage.setTouser(openid);
    //    templateMessage.setTemplateId("2LUnK_0RWUkgzCgaZrPzNslF7dKZOZRihgf-Lge8rb8");
    //    templateMessage.setUrl("www.baidu.com");
    //    String content = String.format(WechatTemplateMessageUtils.TEMPLATE_DATA1, "hello", "word1", "word2", "word3", "remark");
    //    templateMessage.setData(content);
    //    WechatTemplateMessageUtils.sendTemplateMessage(accessToken, templateMessage);
    //}
}
