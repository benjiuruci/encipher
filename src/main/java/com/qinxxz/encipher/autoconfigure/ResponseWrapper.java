package com.qinxxz.encipher.autoconfigure;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;

/**
 * Response包装类
 *
 * @author qinxianzhong
 * @since 2023/8/24
 */
public class ResponseWrapper extends HttpServletResponseWrapper {


    private ServletOutputStream servletOutputStream;

    private ByteArrayOutputStream output;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() {
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
                public void write(int b) {
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
