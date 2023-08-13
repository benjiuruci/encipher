package com.qinxxz.encipher.core;

/**
 * @author qinxianzhong
 * @since 2023/8/11 10:37:09
 */
public class Constant {


    /**
     * AES加密
     */
    public static final String AES = "AES";

    /**
     * RSA加密
     */
    public static final String RSA = "RSA";

    /**
     * 编码
     */
    public static final String ENCODING = "UTF-8";


    /**
     * AES加密/CBC向量工作模式/PKCS填充方式
     */
    public static final String AES_CBC_PKCS = "AES/CBC/PKCS5Padding";

    /**
     * RSA加密/ECB无向量工作模式/PKCS填充方式
     */
    public static final String RSA_EBC_PKCS = "RSA/ECB/PKCS1Padding";

}
