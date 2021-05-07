package com.liurui.Demo5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liu-rui
 * @date 2020/4/28 下午2:06
 * @description
 * 实践IdleStateHandler，参数有：读空闲，写空闲，读写空闲
 * 使用Demo4的Client作为客户端
 *
 * 设置读空闲5s，写空闲7s，读写空闲10s
 *
 * 结论：
 * 1. 当客户端没有任何输入的时候，每隔5s触发读空闲事件，每隔7s触发写空闲事件，每个10s触发读写空闲事件
 * 2. 当客户端发送消息，不会产生读空闲事件和读写空间事件，但会产生写空闲事件，因为server没有write数据
 * @since
 */
@Slf4j
public class MyIdleServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyIdleChannelInitializer());

            final ChannelFuture channelFuture = serverBootstrap.bind(8965).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    static class MyIdleChannelInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(8 * 1024, Delimiters.lineDelimiter()))
                    .addLast(new StringDecoder())
                    .addLast(new StringEncoder())
                    .addLast(new IdleStateHandler(5, 7, 10))
                    .addLast(new MyIdleServerHandler());
        }
    }


    static class MyIdleServerHandler extends SimpleChannelInboundHandler<String> {


        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            super.userEventTriggered(ctx, evt);
            final IdleStateEvent event = (IdleStateEvent) evt;
            log.info("{} {}", ctx.channel().remoteAddress(), event.state());
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            log.info("{} active", ctx.channel().remoteAddress());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
            cause.printStackTrace();
            ctx.channel().close();
        }
    }
}
