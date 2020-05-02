package com.liurui.Demo8;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 通过NIO实现聊天服务端
 * <p>
 * 需求：通过NIO实现多个客户端聊天
 */
@Slf4j
public class Demo9 {
    public static void main(String[] args) throws IOException {
        //存储当前活跃的channel，用于广播消息
        Set< SocketChannel> channelSet = new HashSet<>(16);

        try (Selector selector = Selector.open()) {
            try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

                serverSocketChannel.configureBlocking(false);//设置为非阻塞
                serverSocketChannel.bind(new InetSocketAddress(8991));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//注册到selector,监听accept

                while (true) {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    for (SelectionKey selectionKey : selectionKeys) {
                        if (selectionKey.isAcceptable()) {//有连接
                            ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel socketChannel = serverSocketChannel1.accept();

                            socketChannel.configureBlocking(false);//配置当前连接为非阻塞
                            socketChannel.register(selector, SelectionKey.OP_READ);//通过selector监听read
                            channelSet.add(socketChannel);
                            log.info("{} 建立连接" , ((InetSocketAddress) socketChannel.getRemoteAddress()).getPort());
                        }else if ( selectionKey.isReadable()){
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            ByteBuffer  byteBuffer = ByteBuffer.allocateDirect(1024);

                            //添加发送方的端口号
                            byteBuffer.put(String.format("%s:" , ((InetSocketAddress)socketChannel.getRemoteAddress()).getPort()).getBytes());
                            socketChannel.read(byteBuffer);
                            byteBuffer.flip(); //切换到读取模式

                            for (SocketChannel channel : channelSet) {
                                if(channel.equals(socketChannel)){
                                    continue; //不会给发送发广播消息
                                }
                                byteBuffer.rewind();//倒带，需要反复的发送
                                channel.write(byteBuffer);
                            }
                        }
                    }
                    selectionKeys.clear();//一定要清空，不然会重复消费
                }
            }
        }
    }
}
