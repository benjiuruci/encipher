package com.qinxxz.encipher.autoconfigure;


import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 自动配置
 *
 * @author qinxianzhong
 * @since 2023/8/17 16:24:41
 */
@AutoConfiguration
public class EncipherAutoConfigure {

    private AnnotationSelect annotationSelect;



    @Bean
    public EncipherAutoConfigure autoConfigure(){
        annotationSelect = new AnnotationSelect();

        annotationSelect.scanAnnotation();
        return this;
    }

}
