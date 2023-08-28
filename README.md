# 简介

基于SpringBoot管理接口加解密，加密方式：AES，JDK17，SpringBoot3.0

# 使用方式

首先使用 GenerateKeyUtils.generateKey 生成32位的密钥和16位的向量密钥
直接在application.yml里面配置


``` xml
spring:
    encipher:
        secretKey: oQMmjRp5zeGz2MVvVpq6swxbvLMPopfB  //密钥
        vectorKey: qcFcFwRy5j3lAecC //向量密钥
```
提供两个注解   @Encryption 加密  @Decryption 解密 直接放在方法上

``` xml
    @Encryption
    @Decryption
    @PostMapping("/setUser")
    public void setUser(String name,String address){
        System.out.println("name:" + name);
        System.out.println("address:" + address);
    }
```







