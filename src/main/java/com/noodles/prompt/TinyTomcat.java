package com.noodles.prompt;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个简单的服务器
 * 通过Uri来获取静态文件
 * @author gaoxin.wei
 */
public class TinyTomcat {

    private boolean shutdown = false;

    public static final String WEB_ROOT = "webroot";

    public void await() {

        ServerSocket serverSocket = initServerSocket();
        Preconditions.checkNotNull(serverSocket);

        while (!shutdown) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                Request request = Request.newInstance(input);
                Response response = Response.newInstance(request, output);

                request.parse();
                response.renderStaticResource();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private ServerSocket initServerSocket() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080, 2, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverSocket;
    }
}
