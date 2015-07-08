package com.noodles.prompt;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;

/**
 * 一个简单的Request，封装inputStream和URI
 * 解析http头，获取Uri
 * @author gaoxin.wei
 */
public class Request {

    private InputStream inputStream;

    @Getter
    private String uri;

    private Request() {}

    private Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public static Request newInstance(InputStream inputStream) {
        return new Request(inputStream);
    }

    public void parse() {
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }

        for (int j = 0; j < i; j++)
            request.append((char) buffer[j]);

        System.out.print(request.toString());
        uri = parseUri(request.toString());
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return requestString.substring(index1 + 1, index2);
        }
        return "";
    }


}
