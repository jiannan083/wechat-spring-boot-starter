package cn.wangjiannan.wechart.autoconfigure;

import cn.wangjiannan.wechart.WechartMessageUtils;
import cn.wangjiannan.wechart.WechartUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * 自动化配置.
 *
 * @author wangjiannan
 */
@Configuration //开启配置
@EnableConfigurationProperties(WechartProperties.class) //开启使用映射实体对象
//@ConditionalOnClass(WechartBean.class) //存在WechartPropertyBean.class时初始化该配置类
@ConditionalOnProperty // 存在对应配置信息时初始化该配置类
        (
                prefix = "wechart", //存在配置前缀hello
                value = "enabled", //开启
                matchIfMissing = true //缺失检查
        )
public class WechartAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(WechartAutoConfiguration.class);

    private final WechartProperties properties;

    public WechartAutoConfiguration(WechartProperties properties) {
        this.properties = properties;
    }

    // 构造函数之后执行
    @PostConstruct
    public void checkConfig() {
        if (!StringUtils.hasText(properties.getAppid())) {
            Assert.state(false, "Cannot find wechart config:appid");
        }
        if (!StringUtils.hasText(properties.getSecret())) {
            Assert.state(false, "Cannot find wechart config:secret");
        }
        if (!StringUtils.hasText(properties.getToken())) {
            Assert.state(false, "Cannot find wechart config:token");
        }
    }


    @Bean
    @ConditionalOnMissingBean//缺失时，初始化bean并添加到SpringIoc
    public WechartUtils wechartUtils() {
        logger.info(">>>The WechartUtils Not Found，Execute Create New Bean.");
        WechartUtils wechartUtils = new WechartUtils(properties.getAppid(), properties.getSecret(), properties.getToken());
        return wechartUtils;
    }

    @Bean
    @ConditionalOnMissingBean//缺失时，初始化bean并添加到SpringIoc
    public WechartMessageUtils wechartMessageUtils() {
        logger.info(">>>The WechartMessageUtils Not Found，Execute Create New Bean.");
        return new WechartMessageUtils();
    }

}
