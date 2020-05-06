package com.liurui.Demo9;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {
    public static void main(String[] args) throws  Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8992));

        long beign = System.currentTimeMillis();
        FileInputStream inputStream = new FileInputStream("/Users/liurui/mine/software/jdk-7u71-macosx-x64.dmg");
        FileChannel inputStreamChannel = inputStream.getChannel();
        long read = inputStreamChannel.transferTo(0, inputStreamChannel.size(), socketChannel);
        System.out.println(System.currentTimeMillis() - beign);
        System.out.println(read);
    }
}
