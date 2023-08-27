package com.qinxxz.encipher.autoconfigure;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Response包装类
 *
 * @Author: qinxianzhong
 * @Date: 2023/8/24
 */
public class ResponseWrapper extends HttpServletResponseWrapper {


    private ServletOutputStream servletOutputStream;

    private ByteArrayOutputStream output;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (servletOutputStream == null){
            servletOutputStream = new ServletOutputStream() {
                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setWriteListener(WriteListener listener) {

                }

                @Override
                public void write(int b) throws IOException {
                    output.write(b);
                }
            };
        }
        return servletOutputStream;
    }

    public String getResponseData(){
        return output.toString();
    }



}
