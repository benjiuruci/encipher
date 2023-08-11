package com.qinxxz.encipher;

import com.qinxxz.encipher.core.AESEncryption;
import com.qinxxz.encipher.core.GenerateKeyUtils;

/**
 * @author qinxianzhong
 * @since 2023/8/11 9:03:03
 */
public class Main {
    public static void main(String[] args) {

        AESEncryption aesEncryption = new AESEncryption();

        try {

            String content = """
                    Carefully read both the name and description to ensure that this mapping is an appropriate fit.
                    Do not try to 'force' a mapping to a lower-level Base/Variant simply to comply with this preferred level of abstraction.
                    """;

            String key = GenerateKeyUtils.generateKey(32);
            String shifting = GenerateKeyUtils.generateKey(16);
            System.out.println("密钥：" + key);
            System.out.println("偏移量：" + shifting);
            String jiami = aesEncryption.encryption(content, key, shifting);
            System.out.println("加密：" + jiami);
            System.out.println(" ");
            System.out.println("解密：" + aesEncryption.decrypt(jiami, key, shifting));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}