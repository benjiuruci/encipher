package com.qinxxz.encipher.core;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


/**
 * AES加密
 *
 * @author qinxianzhong
 * @since 2023/8/11 10:58:32
 */
public class AESEncryption {

    /**
     * AES加密
     *
     * @param content   要加密的内容
     * @param secretKey 密钥
     * @param vectorKey 向量密钥
     * @return 密文
     */
    public String encryption(String content, String secretKey, String vectorKey) {
        try {
            //实例化加密对象
            Cipher cipher = Cipher.getInstance(Constant.AES_CBC_PKCS);

            //实例化加密密钥
            SecretKeySpec spec = new SecretKeySpec(secretKey.getBytes(Constant.ENCODING), Constant.AES);

            //偏移量(CBC向量模式下使用)
            IvParameterSpec ips = new IvParameterSpec(vectorKey.getBytes());

            //操作模式为加密
            cipher.init(Cipher.ENCRYPT_MODE, spec, ips);

            //加密内容
            byte[] encryptByte = cipher.doFinal(content.getBytes(Constant.ENCODING));

            //加密内容转换成base64
            return Base64.getEncoder().encodeToString(encryptByte);
        } catch (Exception e) {
            throw new RuntimeException("AES加密失败！" + e);
        }

    }

    /**
     * 解密
     *
     * @param content   加密密文
     * @param secretKey 密钥
     * @param vectorKey 向量密钥
     * @return 明文
     */
    public String decrypt(String content, String secretKey, String vectorKey) {
        try {
            //实例化解密对象
            Cipher cipher = Cipher.getInstance(Constant.AES_CBC_PKCS);

            //实例化解密密钥
            SecretKeySpec spec = new SecretKeySpec(secretKey.getBytes(Constant.ENCODING), Constant.AES);

            //偏移量(CBC向量模式下使用)
            IvParameterSpec ips = new IvParameterSpec(vectorKey.getBytes());

            //操作模式为解密
            cipher.init(Cipher.DECRYPT_MODE, spec, ips);

            //base64密文解码
            byte[] base64 = Base64.getDecoder().decode(content.getBytes(Constant.ENCODING));

            //再使用cipher解密
            return new String(cipher.doFinal(base64), Constant.ENCODING);
        } catch (Exception e) {
            throw new RuntimeException("AES解密失败！" + e);
        }

    }

}
