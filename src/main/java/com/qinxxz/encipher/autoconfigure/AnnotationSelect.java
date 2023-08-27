package com.qinxxz.encipher.autoconfigure;

import com.qinxxz.encipher.annotation.Decryption;
import com.qinxxz.encipher.annotation.Encryption;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 注解扫描器
 *
 * @author qinxianzhong
 * @since 2023/8/17 17:53:04
 */
public class AnnotationSelect implements ApplicationContextAware {
    /**
     * 解密注解url路径集合
     */
    public static List<String> decryptionList = new ArrayList<>();

    /**
     * 加密注解url路径集合
     */
    public static List<String> encryptionList = new ArrayList<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {

        Map<RequestMappingInfo,HandlerMethod> handlerMethodMap = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();


        for (Map.Entry<RequestMappingInfo,HandlerMethod> infoEntry : handlerMethodMap.entrySet()){
            HandlerMethod handlerMethod = infoEntry.getValue();
            Decryption decryption = handlerMethod.getMethodAnnotation(Decryption.class);
            Encryption encryption = handlerMethod.getMethodAnnotation(Encryption.class);
            //获取uri路径地址
            Set<PathPattern> patterns = null;
            if (infoEntry.getKey().getPathPatternsCondition() != null) {
                patterns = infoEntry.getKey().getPathPatternsCondition().getPatterns();
            }
            if (patterns != null) {
                for(PathPattern url :patterns) {
                    if (decryption != null){
                        decryptionList.add(url.getPatternString());
                    }
                    if (encryption != null){
                        encryptionList.add(url.getPatternString());
                    }
                }
            }


        }
    }




}
