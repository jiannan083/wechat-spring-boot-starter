package cn.bfay.wechat.autoconfigure;

import cn.bfay.wechat.WechatCoreManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * 自动化配置.
 *
 * @author wangjiannan
 */
@Configuration //开启配置
@EnableConfigurationProperties(WechatProperties.class) //开启使用映射实体对象
//@ConditionalOnClass(WechatToken.class) //存在WechatPropertyBean.class时初始化该配置类
@ConditionalOnProperty // 存在对应配置信息时初始化该配置类
    (
        prefix = "wechat", //存在配置前缀hello
        value = "enabled", //开启
        matchIfMissing = true //缺失检查
    )
public class WechatAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(WechatAutoConfiguration.class);

    private final WechatProperties properties;

    public WechatAutoConfiguration(WechatProperties properties) {
        this.properties = properties;
    }

    // 构造函数之后执行
    @PostConstruct
    public void checkConfig() {
        Assert.hasText(properties.getAppid(), "Cannot find wechat config:appid");
        Assert.hasText(properties.getSecret(), "Cannot find wechat config:secret");
        Assert.hasText(properties.getToken(), "Cannot find wechat config:token");
        //if (!StringUtils.hasText(properties.getAppid())) {
        //    Assert.state(false, "Cannot find wechat config:appid");
        //}
        //if (!StringUtils.hasText(properties.getSecret())) {
        //    Assert.state(false, "Cannot find wechat config:secret");
        //}
        //if (!StringUtils.hasText(properties.getToken())) {
        //    Assert.state(false, "Cannot find wechat config:token");
        //}
    }


    @Bean
    @ConditionalOnMissingBean//缺失时，初始化bean并添加到SpringIoc
    public WechatCoreManager wechatCoreManager() {
        log.info(">>>The WechatCoreManager Not Found，Execute Create New Bean.");
        WechatCoreManager wechatCoreManager = new WechatCoreManager(properties.getAppid(), properties.getSecret(), properties.getToken());
        return wechatCoreManager;
    }
}
