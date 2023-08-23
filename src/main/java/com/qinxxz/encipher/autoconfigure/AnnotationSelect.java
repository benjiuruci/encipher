package com.qinxxz.encipher.autoconfigure;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 注解扫描器
 *
 * @author qinxianzhong
 * @since 2023/8/17 17:53:04
 */
@Component
public class AnnotationSelect implements ApplicationContextAware {

    /**
     * 解密注解url路径集合
     */
    private static List<String> decryptList = new ArrayList<>();

    /**
     * 加密注解url路径集合
     */
    private static List<String> encryptionList = new ArrayList<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {

    }
}
