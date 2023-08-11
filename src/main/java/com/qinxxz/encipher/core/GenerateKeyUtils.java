package com.qinxxz.encipher.core;

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




}
