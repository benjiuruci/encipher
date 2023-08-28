package com.qinxxz.encipher.autoconfigure;


import com.qinxxz.encipher.core.AESEncryption;
import com.qinxxz.encipher.core.RSAEncryption;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置
 *
 * @author qinxianzhong
 * @since 2023/8/17 16:24:41
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(EncipherConfig.class)
public class EncipherAutoConfigure {

    @Resource
    private EncipherConfig encipherConfig;


    @Bean
    public FilterRegistrationBean<EncipherFilter> registrationBean() {
        FilterRegistrationBean<EncipherFilter> registrationBean = new FilterRegistrationBean<EncipherFilter>();
        registrationBean.setFilter(new EncipherFilter(encipherConfig,new AESEncryption(),new RSAEncryption()));
        registrationBean.setOrder(1);
        registrationBean.setName("EncipherFilter");
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public AnnotationSelect annotationSelect() {
        return new AnnotationSelect();
    }


}
