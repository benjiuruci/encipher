package com.qinxxz.encipher.autoconfigure;

import com.qinxxz.encipher.annotation.Decrypt;
import com.qinxxz.encipher.annotation.Encryption;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public static List<String> decryptList = new ArrayList<>();

    /**
     * 加密注解url路径集合
     */
    public static List<String> encryptionList = new ArrayList<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {

        //获取带Controller注解的bean
        Map<String, Object> controller = applicationContext.getBeansWithAnnotation(Controller.class);

        for (Map.Entry<String, Object> entry : controller.entrySet()) {
            Class<?> cls = entry.getValue().getClass();
            Method[] methods = cls.getMethods();
            //循环Controller下的方法
            for (Method method : methods) {
                //判断方法下是否有加密注解
                Encryption encryption = AnnotationUtils.findAnnotation(method, Encryption.class);
                if (encryption != null) {
                    encryptionList.add(getApiUrl(cls, method));
                }
                Decrypt decrypt = AnnotationUtils.findAnnotation(method, Decrypt.class);
                if (decrypt != null) {
                    decryptList.add(getApiUrl(cls, method));
                }

            }

        }
    }


    private String getApiUrl(Class<?> clz, Method method) {
        StringBuilder uri = new StringBuilder();

        RequestMapping clzRequestMapping = AnnotationUtils.findAnnotation(clz, RequestMapping.class);
        if (clzRequestMapping != null) {
            uri.append(formatUri(clzRequestMapping.value()[0]));
        }

        PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
        GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
        DeleteMapping deleteMapping = AnnotationUtils.findAnnotation(method, DeleteMapping.class);
        PutMapping putMapping = AnnotationUtils.findAnnotation(method, PutMapping.class);
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);

        if (postMapping != null) {
            uri.append(formatUri(postMapping.value()[0]));
        }
        if (getMapping != null) {
            uri.append(formatUri(getMapping.value()[0]));
        }
        if (deleteMapping != null) {
            uri.append(formatUri(deleteMapping.value()[0]));
        }
        if (putMapping != null) {
            uri.append(putMapping.value()[0]);
        }
        return uri.toString();
    }


    private String formatUri(String uri) {
        if (uri.startsWith("/")) {
            return uri;
        }
        return "/" + uri;
    }




}
