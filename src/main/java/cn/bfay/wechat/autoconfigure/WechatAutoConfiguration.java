package cn.bfay.wechat;

import cn.bfay.wechat.client.WechatClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * WechatAutoConfiguration.
 *
 * @author wangjiannan
 * @since 2019/10/24
 */
@Configuration
@EnableConfigurationProperties(WechatProperties.class)
// 存在对应配置信息时初始化该配置类
public class WechatAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(WechatAutoConfiguration.class);

    @Resource
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
    }

    @Bean
    //缺失时，初始化bean并添加到SpringIoc
    @ConditionalOnMissingBean
    public WechatCoreManager wechatCoreManager() {
        log.info(">>>The WechatCoreManager Not Found，Execute Create New Bean.");
        return new WechatCoreManager(properties.getAppid(), properties.getSecret(), properties.getToken(), wechatClient);
    }
}
