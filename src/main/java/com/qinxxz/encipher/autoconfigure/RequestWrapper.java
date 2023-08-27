package com.qinxxz.encipher.autoconfigure;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Request包装类
 *
 * @Author: qinxianzhong
 * @Date: 2023/8/24
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private  Map<String,String> parameterMap;

    private byte[] requestBody = new byte[0];


    public RequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            requestBody = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return arrayInputStream.read();
            }
        };
    }


    public String getRequestData() {
        return new String(requestBody);
    }

    public void setRequestData(String requestData){
        this.requestBody = requestData.getBytes();
    }

    public void setParameterMap(Map<String,String> parameterMap){
        this.parameterMap = parameterMap;
    }

    @Override
    public String getParameter(String name){
        return this.parameterMap.get(name);
    }

    @Override
    public String[] getParameterValues(String name){
        if (parameterMap.containsKey(name)){
            return new String[] {
                getParameter(name)
            };
        }
        return super.getParameterValues(name);
    }

}
