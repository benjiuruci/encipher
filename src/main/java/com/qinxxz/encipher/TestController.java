package com.qinxxz.encipher;

import com.qinxxz.encipher.annotation.Decrypt;
import com.qinxxz.encipher.annotation.Encryption;
import com.qinxxz.encipher.autoconfigure.AnnotationSelect;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: qinxianzhong
 * @Date: 2023/8/24
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Encryption
    @Decrypt
    @PostMapping("/getName")
    public String getName(){

        return AnnotationSelect.encryptionList.toString();
    }
}
