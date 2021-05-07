package com.liurui.Demo9;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 采用零拷贝方式，测试性能，对比传统方式性能提升提升多少
 */
public class NewIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8992));

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(8 * 1024);
            int max = 0;

            while (true) {
                int read = socketChannel.read(byteBuffer);

                if (read == -1) {
                    break;
                }
                max += read;
                byteBuffer.rewind();
            }
            System.out.println(max);
        }
    }
}
