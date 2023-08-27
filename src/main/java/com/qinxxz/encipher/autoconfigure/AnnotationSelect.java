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

            //判断该方法上是否有解密注解
            if (decryption != null){
                //获取uri路径地址
                Set<PathPattern> patterns = infoEntry.getKey().getPathPatternsCondition().getPatterns();
                for(PathPattern url :patterns) {
                    decryptionList.add(url.getPatternString());
                }
            }
            //判断该方法上是否有加密注解
            if (encryption != null){
                //获取uri路径地址
                Set<PathPattern> patterns = infoEntry.getKey().getPathPatternsCondition().getPatterns();
                for(PathPattern url :patterns) {
                    encryptionList.add(url.getPatternString());
                }
            }

        }
    }




}
