package com.myRedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public void run(int port) throws IOException {
        // 建立一个 Listen 在 port 上的 TCP 监听 Socket
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                // 一次循环，处理一个用户请求

                // 通过 accept 方法调用，返回一个代表建立好连接的 TCP Socket
                try (Socket socket = serverSocket.accept()) {
                    logger.info("{} 已连接。", socket.getInetAddress().getHostName());
                    // is 和 os 分别代表连接中的输入/输出流
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();

                    Command command = null;
                    while (true){
                        try {
                            command = Protocol.readCommand(is);
                            command.run(os);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Protocol.writeError(os,"undefined");
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server().run(6379);
    }
}
