package com.liurui.Demo9;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class OldIOClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8991);
        OutputStream outputStream = socket.getOutputStream();
        long begin = System.currentTimeMillis();
        FileInputStream inputStream = new FileInputStream("/Users/liurui/mine/software/jdk-7u71-macosx-x64.dmg");
        byte[] bytes = new byte[8 * 1024];
        int max = 0;
        while (true) {
            int read = inputStream.read(bytes);
            if (read == -1) {
                break;
            }

            outputStream.write(bytes, 0, read);
            max += read;
        }
        System.out.println(System.currentTimeMillis() - begin);
        System.out.println(max);
    }
}
