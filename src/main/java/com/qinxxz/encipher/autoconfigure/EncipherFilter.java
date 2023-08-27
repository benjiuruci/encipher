package com.qinxxz.encipher.autoconfigure;

import com.qinxxz.encipher.core.AESEncryption;
import com.qinxxz.encipher.core.Constant;
import com.qinxxz.encipher.core.RSAEncryption;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 加解密过滤器
 *
 * @author qinxianzhong
 * @since 2023/8/24
 */
public class EncipherFilter implements Filter {

    private final EncipherConfig encipherConfig;

    private final AESEncryption aes;

    private final RSAEncryption rsa;

    public EncipherFilter(EncipherConfig encipherConfig,AESEncryption aes,RSAEncryption rsa){
        this.encipherConfig = encipherConfig;
        this.aes = aes;
        this.rsa = rsa;
    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        RequestWrapper requestWrapper = null;
        ResponseWrapper responseWrapper = null;

        String uri = request.getRequestURI();
        boolean isContainsDecrypt = contains(AnnotationSelect.decryptionList, uri);
        boolean isContainsEncryption = contains(AnnotationSelect.encryptionList, uri);


        if (!isContainsDecrypt && !isContainsEncryption) {
            filterChain.doFilter(request, response);
        }

        //解密操作
        if (isContainsDecrypt) {
            requestWrapper = new RequestWrapper(request);
            processDecryption(requestWrapper, request);
        }

        if (isContainsEncryption) {
            responseWrapper = new ResponseWrapper(response);
        }

        if (isContainsDecrypt && isContainsEncryption) {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } else if (isContainsEncryption) {
            filterChain.doFilter(request, responseWrapper);
        } else if (isContainsDecrypt) {
            filterChain.doFilter(requestWrapper, response);
        }

        if (isContainsEncryption) {
            String responseData = responseWrapper.getResponseData();
            writeEncryption(responseData, response);
        }


    }



    private void processDecryption(RequestWrapper requestWrapper,HttpServletRequest request){
        String requestData = requestWrapper.getRequestData();
        if (!StringUtils.endsWithIgnoreCase(request.getMethod(), RequestMethod.GET.name())) {
            String encryptData = aes.decrypt(requestData, encipherConfig.secretKey(), encipherConfig.vectorKey());
            requestWrapper.setRequestData(encryptData);
        }
        Map<String, String> paramMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            String decryptParamValue = aes.decrypt(paramValue, encipherConfig.secretKey(), encipherConfig.vectorKey());
            paramMap.put(paramName, decryptParamValue);

        }
        requestWrapper.setParameterMap(paramMap);



    }


    private void writeEncryption(String responseData, ServletResponse response) throws IOException {
        ServletOutputStream output = null;

        try {
            String encryptionContent = aes.encryption(responseData, encipherConfig.secretKey(), encipherConfig.vectorKey());
            response.setContentLength(encryptionContent.length());
            response.setCharacterEncoding(Constant.ENCODING);
            output = response.getOutputStream();
            output.write(encryptionContent.getBytes(Constant.ENCODING));
        } catch (IOException e) {
            throw new RuntimeException("响应数据加密失败：" + e);
        } finally {
            if (output != null) {
                output.flush();
                output.close();
            }
        }
    }



    private boolean contains(List<String> list,String uri){
        return list.contains(uri);
    }
}
