package com.liurui.Demo4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author liu-rui
 * @date 2020/4/27 上午9:23
 * @description
 * @since
 */
public class MyChatClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new MyChatClientChannelInitializer());

            final ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8965).sync();

            Thread t1 = new Thread(() -> {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (true) {
                        final String message = bufferedReader.readLine();

                        if (StringUtil.isNullOrEmpty(message)) {
                            continue;
                        }
                        channelFuture.channel().writeAndFlush(message + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, "键盘");

            t1.setDaemon(true);
            t1.start();


            channelFuture.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
