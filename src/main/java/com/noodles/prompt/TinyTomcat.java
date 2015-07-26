package com.noodles.prompt;

import com.google.common.base.Preconditions;
import com.noodles.prompt.processor.ServletProcessor;
import com.noodles.prompt.processor.StaticResoureProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个简单的服务器
 * 通过Uri来获取静态c文件
 * @author gaoxin.wei
 */
public class TinyTomcat {

    private boolean shutdown = false;

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

                // todo 执行动态或静态
                if (isDynamicRequest(request))
                    new ServletProcessor().process(request, response);
                else
                    new StaticResoureProcessor().process(request, response);

            } catch (Exception e) {
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

    private boolean isDynamicRequest(Request request) {
        // todo
        return false;
    }
}
