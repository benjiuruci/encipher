package com.qinxxz.encipher.autoconfigure;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @Author: qinxianzhong
 * @Date: 2023/8/24
 */
public class ResponseWrapper extends HttpServletRequestWrapper {

    public ResponseWrapper(HttpServletRequest request) {
        super(request);
    }

}
