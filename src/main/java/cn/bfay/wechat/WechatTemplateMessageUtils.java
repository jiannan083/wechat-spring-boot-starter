package cn.bfay.wechat;

import cn.bfay.commons.okhttp.OkHttpUtils;
import cn.bfay.wechat.model.TemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模板消息接口.
 *
 * @author wangjiannan
 */
public class WechatTemplateMessageUtils {
    private static final Logger log = LoggerFactory.getLogger(WechatTemplateMessageUtils.class);

    public static String TEMPLATE_DATA1 = "{\"first\":{\"value\":\"%s\"},\"keyword1\":{\"value\":\"%s\"}," +
            "\"keyword2\":{\"value\":\"%s\"},\"keyword3\":{\"value\":\"%s\"},\"remark\":{\"value\":\"%s\"}}";

    /**
     * 设置所属行业.
     *
     * @param accessToken access_token
     * @param industryId1 公众号模板消息所属行业编号
     * @param industryId2 公众号模板消息所属行业编号
     */
    public static void setIndustry(String accessToken, String industryId1, String industryId2) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s", accessToken);
        String content = String.format("{\"industry_id1\":\"%s\",\"industry_id2\":\"%s\"}", industryId1, industryId2);
        OkHttpUtils.executePost(url, content, String.class);
    }

    /**
     * 获取设置的行业信息.
     *
     * @param accessToken access_token
     */
    public static void getIndustry(String accessToken) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s", accessToken);
        String result = OkHttpUtils.executeGet(url, String.class);
    }


    /**
     * 获得模板ID.
     *
     * @param accessToken     access_token
     * @param templateIdShort 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     */
    public static void addTemplate(String accessToken, String templateIdShort) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=%s", accessToken);
        String content = String.format("\"template_id_short\":\"%s\"", templateIdShort);
        String result = OkHttpUtils.executePost(url, content, String.class);
    }

    /**
     * 获取模板列表.
     *
     * @param accessToken access_token
     */
    public static void getAllPrivateTemplate(String accessToken) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s", accessToken);
        String result = OkHttpUtils.executeGet(url, String.class);
    }

    /**
     * 删除模板.
     *
     * @param accessToken access_token
     * @param templateId  公众帐号下模板消息ID
     */
    public static void delPrivateTemplate(String accessToken, String templateId) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=%s", accessToken);
        String content = String.format("\"template_id\":\"%s\"", templateId);
        String result = OkHttpUtils.executePost(url, content, String.class);
    }

    /**
     * 发送模板消息.
     *
     * @param accessToken     access_token
     * @param templateMessage {@link TemplateMessage}
     */
    public void sendTemplateMessage(String accessToken, TemplateMessage templateMessage) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s", accessToken);
        String content = String.format("{\"touser\":\"%s\",\"template_id\":\"%s\",\"url\":\"%s\",\"miniprogram\":{\"appid\":\"%s\",\"pagepath\":\"%s\"},\"data\":%s}",
                templateMessage.getTouser(), templateMessage.getTemplateId(), templateMessage.getUrl(),
                templateMessage.getAppid(), templateMessage.getPagepath(), templateMessage.getData());
        String result = OkHttpUtils.executePost(url, content, String.class);
    }
}
