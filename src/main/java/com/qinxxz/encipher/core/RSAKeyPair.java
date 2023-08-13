package com.qinxxz.encipher.core;

/**
 * RSA密钥对对象
 *
 * @Author: qinxianzhong
 * @Date: 2023/8/13
 */
public class RSAKeyPair {

    private String publicKey;

    private String privateKey;

    public RSAKeyPair(){

    }

    public RSAKeyPair(String publicKey, String privateKey){
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }


    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
