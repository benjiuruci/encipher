package com.qinxxz.encipher.core;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA加密
 *
 * @author qinxianzhong
 * @since 2023/8/11 16:45:00
 */
public class RSAEncryption {


    /**
     * 使用私钥加密或解密
     *
     * @param content          要加解密的内容
     * @param model            1.表示加密模式 2.表示解密模式
     * @param privateKey       私钥
     * @return 加解密的数据
     */
    public String byPrivateKey(String content,int model,String privateKey ){
        try {
            //实例化PKCS#8标准编码私钥规范
            EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey.getBytes()));
            //实例化RSA密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(Constant.RSA);
            //从提供的密钥规范生成私钥对象
            PrivateKey keyPrivate = keyFactory.generatePrivate(encodedKeySpec);
            //加解密数据
            return encryptionDecryption(content, model, keyPrivate);
        } catch (Exception e) {
            throw new RuntimeException("使用私钥加密或解密失败！", e);
        }
    }


    /**
     * 使用公钥加密或解密
     *
     * @param content          要加解密的内容
     * @param model            1.表示加密模式 2.表示解密模式
     * @param publicKey        公钥
     * @return 加解密的数据
     */
    public String byPublicKey(String content,int model,String publicKey){
        try {
            //实例化X509标准编码公钥规范
            EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey.getBytes()));
            //实例化RSA密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(Constant.RSA);
            //从提供的密钥规范生成公钥对象
            PublicKey keyPublic = keyFactory.generatePublic(encodedKeySpec);
            //加密或解密
            return encryptionDecryption(content, model, keyPublic);
        } catch (Exception e) {
            throw new RuntimeException("使用公钥加密或解密失败！", e);
        }
    }



    /**
     * 加密或解密
     * @param content 要加解密的内容
     * @param model 1.表示加密模式 2.表示解密模式
     * @param key 公钥或私钥的Key
     * @return
     */
    public String encryptionDecryption(String content, int model, Key key) throws Exception{
        String result = "";
        Cipher cipher = Cipher.getInstance(Constant.RSA_EBC_PKCS);
        cipher.init(model, key);
        //如果是解密模式，则需要使用Base64进行解码
        byte[] contentBytes = content.getBytes(Constant.ENCODING);
        if (model == Cipher.DECRYPT_MODE) {
            contentBytes = Base64.getDecoder().decode(contentBytes);
        }
        byte[] finalBytes = cipher.doFinal(contentBytes);
        //如果是加密，则将加密后的字节数组使用 Base64 转成可视化的字符串。否则是解密时，直接 new String 字符串.
        if (model == Cipher.ENCRYPT_MODE) {
            result = Base64.getEncoder().encodeToString(finalBytes);
        } else if (model == Cipher.DECRYPT_MODE) {
            result = new String(finalBytes);
        }
        return result;
    }

    /**
     * 签名
     * @param content 要签名的内容
     * @param privateKey
     * @return
     */
    public String sign(String content,String privateKey){
        try {
            //实例化PKCS#8标准编码私钥规范
            EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey.getBytes()));
            //实例化RSA密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(Constant.RSA);
            //从提供的密钥规范生成私钥对象
            PrivateKey keyPrivate = keyFactory.generatePrivate(encodedKeySpec);
            //实例化签名对象
            Signature signature = Signature.getInstance("SHA512withRSA");
            signature.initSign(keyPrivate);
            signature.update(content.getBytes());
            return Base64.getEncoder().encodeToString(signature.sign());
        }catch (Exception e){
            throw new RuntimeException("签名失败！",e);
        }
    }

    /**
     * 验签
     * @param content 内容
     * @param publicKey 公钥
     * @param sign 签名
     * @return
     */
    public boolean verify(String content,String publicKey,String sign){
        try{
            //实例化X509标准编码公钥规范
            EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            //实例化RSA密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(Constant.RSA);
            //从提供的密钥规范生成公钥对象
            PublicKey key = keyFactory.generatePublic(encodedKeySpec);
            //实例化签名对象
            Signature signature = Signature.getInstance("SHA512withRSA");
            signature.initVerify(key);
            signature.update(content.getBytes());
            return signature.verify(Base64.getDecoder().decode(sign.getBytes()));
        }catch (Exception e){
            throw new RuntimeException("验签失败！",e);
        }
    }
}
