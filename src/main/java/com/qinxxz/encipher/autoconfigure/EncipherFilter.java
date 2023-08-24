package com.qinxxz.encipher.autoconfigure;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * 加解密过滤器
 *
 * @Author: qinxianzhong
 * @Date: 2023/8/24
 */
public class EncipherFilter implements Filter {




    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        RequestWrapper requestWrapper = null;
        ResponseWrapper responseWrapper = null;

        String uri = request.getRequestURI();
        boolean isContainsDecrypt = contains(AnnotationSelect.decryptList,uri);
        boolean isContainsEncryption = contains(AnnotationSelect.encryptionList,uri);



        if (!isContainsDecrypt && !isContainsEncryption){
            filterChain.doFilter(request,response);
        }

        //解密操作
        if (isContainsDecrypt){
            requestWrapper = new RequestWrapper(request);
        }
    }





    private boolean contains(List<String> list,String uri){
        if (list.contains(uri)){
            return true;
        }
        return false;
    }
}
