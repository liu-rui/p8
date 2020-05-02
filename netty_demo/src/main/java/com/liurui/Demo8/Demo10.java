package com.liurui.Demo8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * 通过NIO实现聊天客户端
 */
public class Demo10 {
    public static void main(String[] args) throws IOException {
        try (Selector selector = Selector.open()) {

            try (SocketChannel socketChannel = SocketChannel.open()) {
                socketChannel.configureBlocking(false);
                socketChannel.connect(new InetSocketAddress(8991));
                socketChannel.register(selector, SelectionKey.OP_CONNECT);

                while (true) {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    for (SelectionKey selectionKey : selectionKeys) {
                        if (selectionKey.isConnectable()) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();

                            if (channel.isConnectionPending()) {
                                channel.finishConnect();
                                channel.register(selector, SelectionKey.OP_READ);

                                new Thread(() -> {
                                    try (BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(System.in))) {
                                        while (true) {
                                            String line = bufferedInputStream.readLine();

                                            if (line == null) {
                                                continue;
                                            }
                                            ByteBuffer byteBuffer = ByteBuffer.wrap(line.getBytes());

                                            channel.write(byteBuffer);
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }, "t1").start();
                            }
                        } else if (selectionKey.isReadable()) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                            channel.read(byteBuffer);
                            byteBuffer.flip();
                            System.out.write(byteBuffer.array(), 0, byteBuffer.limit());
                        }
                    }
                    selectionKeys.clear();
                }
            }
        }
    }
}
