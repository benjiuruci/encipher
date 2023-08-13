package com.qinxxz.encipher;

import com.qinxxz.encipher.core.GenerateKeyUtils;
import com.qinxxz.encipher.core.RSAEncryption;
import com.qinxxz.encipher.core.RSAKeyPair;

/**
 * @author qinxianzhong
 * @since 2023/8/11 9:03:03
 */
public class Main {
    public static void main(String[] args) {

//        AESEncryption aesEncryption = new AESEncryption();
//
//        try {
//
//            String content = """
//                    Carefully read both the name and description to ensure that this mapping is an appropriate fit.
//                    Do not try to 'force' a mapping to a lower-level Base/Variant simply to comply with this preferred level of abstraction.
//                    """;
//
//            String key = GenerateKeyUtils.generateKey(32);
//            String shifting = GenerateKeyUtils.generateKey(16);
//            System.out.println("密钥：" + key);
//            System.out.println("偏移量：" + shifting);
//            String jiami = aesEncryption.encryption(content, key, shifting);
//            System.out.println("加密：" + jiami);
//            System.out.println(" ");
//            System.out.println("解密：" + aesEncryption.decrypt(jiami, key, shifting));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        //RSA加解密

        RSAEncryption rsaEncryption = new RSAEncryption();

        String content = """
                号称“亚洲最大肿瘤医院分院”的河北中国医学科学院肿瘤医院(简称“河北医院”)，近日突然宣布申请注销。
                """;
        String secureRandomSeed = "TPCz0lDvTHybGMsHTJi3mJ7Pt48llJmRHb";

        System.out.println("要加密的数据大小：" + content.getBytes().length + " 字节，" + content.length() + " 个字符");

        System.out.println("原内容：\n" + content);

        //获取密钥对
        RSAKeyPair keyPair = GenerateKeyUtils.generateKeyPair(secureRandomSeed,2048);


        System.out.println("公钥：" + keyPair.getPublicKey());
        System.out.println("私钥：" + keyPair.getPrivateKey());



        try {
            //公钥、私钥必须分开使用，公钥解密时，必须是私钥解密，反之亦然.
            String encrypted = rsaEncryption.byPublicKey(content, 1, keyPair.getPublicKey());

            String decrypted = rsaEncryption.byPrivateKey(encrypted, 2, keyPair.getPrivateKey());
            System.out.println("加密后：\n" + encrypted);
            System.out.println("解密后：\n" + decrypted);



            // RSA签名
            String sign = rsaEncryption.sign(content,keyPair.getPrivateKey() );
            // RSA验签
            boolean result = rsaEncryption.verify(content, keyPair.getPublicKey(), sign);
            System.out.println("验签结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
