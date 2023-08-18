package com.qinxxz.encipher.autoconfigure;

import com.qinxxz.encipher.annotation.Decrypt;
import com.qinxxz.encipher.annotation.Encryption;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 注解扫描器
 *
 * @author qinxianzhong
 * @since 2023/8/17 17:53:04
 */
public class AnnotationSelect {

    private static List<Decrypt> decryptList = new ArrayList<>();

    private static List<Encryption> encryptionList = new ArrayList<>();

    public void scanAnnotation(){

        ApplicationContext applicationContext =


    }




}
