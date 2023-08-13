package com.qinxxz.encipher.core;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

/**
 * 密钥生成工具
 *
 * @author qinxianzhong
 * @since 2023/8/11 10:33:03
 */
public class GenerateKeyUtils {

    private static String characters  = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 生成随机密钥
     *
     * @param size 长度 16 或 32
     * @return 随机密钥
     */
    public static String generateKey(int size){
        StringBuilder key = new StringBuilder(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            key.append(characters.charAt(random.nextInt(characters.length())));
        }
        return key.toString();
    }

    /**
     * 生成RSA密钥对
     * @param secureRandomSeed 随机生成密钥的种子
     * @return 密钥对
     */
    public static RSAKeyPair generateKeyPair(String secureRandomSeed, int size){
        if (size != 1024 && size != 2048 && size != 4096) {
            throw new RuntimeException("密钥长度不正确！" + size);
        }
        try {
            //实例化公钥私钥生成器对象
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(Constant.RSA);

            //使用SHA1PRNG算法生成随机数
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

            //加入默认种子，种子不变生成的密钥不变
            secureRandom.setSeed(secureRandomSeed.getBytes());

            //初始化
            keyPairGenerator.initialize(size, secureRandom);

            //返回密钥对
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            //公钥 Base64编码
            String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

            //私钥 Base64编码
            String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

            return new RSAKeyPair(publicKey, privateKey);
        } catch (Exception e) {
            throw new RuntimeException("获取RSA密钥对失败！", e);
        }
    }
}
