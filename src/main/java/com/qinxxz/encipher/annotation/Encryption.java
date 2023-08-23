package com.qinxxz.encipher.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加密注解
 *
 * @Author: qinxianzhong
 * @Date: 2023/8/17 15:47:51
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Encryption {

    String value() default "";
}
