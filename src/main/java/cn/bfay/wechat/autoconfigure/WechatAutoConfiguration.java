package cn.bfay.wechat.autoconfigure;

import cn.bfay.wechat.WechatCoreUtil;
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
    @ConditionalOnMissingBean(WechatCoreUtil.class)//缺失bean时，初始化并添加到SpringIoc
    public WechatCoreUtil wechatCoreManager() {
        log.info(">>>The WechatCoreUtil Not Found，Execute Create New Bean.");
        return new WechatCoreUtil(properties.getAppid(), properties.getSecret(), properties.getToken());
    }
}
