package com.noodles.prompt;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 一个简单的相应，封装request以及outputStream
 * 通过Request中解析到的Uri，指向静态文件
 * @author gaoxin.wei
 */
public class Response {

    private Request request;
    private OutputStream outputStream;

    private static final int BUFFER_SIZE = 1024;

    private Response() {}

    private Response(Request request, OutputStream outputStream) {
        this.request = request;
        this.outputStream = outputStream;
    }

    public static Response newInstance(Request request, OutputStream outputStream) {
        return new Response(request, outputStream);
    }

    /**
     * 获取静态文件
     */
    public void renderStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        InputStream fs = getInputStream(request);
        try {
            fs = getInputStream(request);
            if (fs != null) {
                int ch = fs.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    outputStream.write(bytes, 0, ch);
                    ch = fs.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                // file not found
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
                outputStream.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            // thrown if cannot instantiate a File object
            System.out.println(e.toString() );
        } finally {
            if (fs!=null)
                fs.close();
        }

    }

    private InputStream getInputStream(Request request) {
        URL url = this.getClass().getClassLoader().getResource(TinyTomcat.WEB_ROOT + request.getUri());
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            return urlConnection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
