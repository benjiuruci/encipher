package com.qinxxz.encipher.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 加密配置
 * @Author: qinxianzhong
 * @Date: 2023/8/14
 */
@ConfigurationProperties("spring.")
public class EncryptionConfig {

    /**
     * 加密算法类型 AES or RSA
     */
    private String type;

    /**
     * AES加密的Key值
     */
    private String key;

    /**
     * RSA加密公钥
     */
    private String publicKey;

    /**
     * RSA加密私钥
     */
    private String privateKey;


}
