package com.liurui.source.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerChannelInitializer());

            final ChannelFuture channelFuture = serverBootstrap.bind(8991).sync();

            channelFuture.channel().close().sync();
            log.info(boss.toString());
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    static class MyServerChannelInitializer extends ChannelInitializer<ServerChannel> {

        @Override
        protected void initChannel(ServerChannel ch) throws Exception {
            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(8 * 1024, Delimiters.lineDelimiter()))
                    .addLast(new StringEncoder())
                    .addLast(new StringDecoder())
                    .addLast(new MyHandler());
        }
    }

    static class MyHandler extends SimpleChannelInboundHandler<String> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            ctx.writeAndFlush(msg);
        }
    }
}
