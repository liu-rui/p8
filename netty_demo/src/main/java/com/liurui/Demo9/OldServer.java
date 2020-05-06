package com.liurui.Demo9;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统IOserver,测试下文件传输的性能
 */
public class OldServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8991);

        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[8 * 1024];
            int max = 0;

            while (true) {
                int read = inputStream.read(bytes);

                if (read == -1) {
                    break;
                }
                max += read;
            }
            System.out.printf("接收到字节数：%s\n", max);
        }
    }
}
