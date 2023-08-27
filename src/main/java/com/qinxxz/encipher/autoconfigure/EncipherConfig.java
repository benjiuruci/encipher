package com.qinxxz.encipher.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * springboot配置
 *
 * @author qinxianzhong
 * @since 2023/8/25
 */
@ConfigurationProperties(prefix = "spring.encipher")
public record EncipherConfig(String secretKey,String vectorKey) {

    public EncipherConfig{

        if (secretKey.length() != 16 && secretKey.length() != 32){
            throw new RuntimeException("secretKey:指定的密钥大小对此算法无效 16位 或 32位");
        }


        if (vectorKey.length() != 16){
            throw new RuntimeException("vectorKey:指定的密钥大小对此算法无效，必须16位");
        }
    }
}
