package com.liurui.Demo8;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 实践Seletor
 * 监听多个端口，接收消息
 */
@Slf4j
public class Demo8 {
    public static void main(String[] args) throws IOException {
        int[] ports = new int[]{5000, 5001, 5002, 5003, 5004};

        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(ports[i]));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            log.info("监听端口{}", ports[i]);
        }

        while (true) {
            int select = selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            log.info("发现key , {}个", select);
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    log.info("与{}建立连接", socketChannel.getRemoteAddress());
                }
                if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                    int max = 0;

                    while (true) {
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);

                        if (read <= 0) {
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        max += read;
                    }
                    log.info("从{} 读取了{}个字节", socketChannel.getRemoteAddress(), max);
                }
                iterator.remove();
            }
        }

    }
}
