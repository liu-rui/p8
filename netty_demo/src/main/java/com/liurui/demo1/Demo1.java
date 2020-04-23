package com.liurui.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author liu-rui
 * @date 2020/4/22 下午5:17
 * @description
 * @since
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerChannelInitializer());

            final ChannelFuture sync = serverBootstrap.bind(18090).sync();

            sync.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    static class MyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            final ChannelPipeline pipeline = ch.pipeline();

            pipeline.addLast(new HttpServerCodec())
                    .addLast(new MyServerChannelhandler());
        }
    }

    @Slf4j
    static class MyServerChannelhandler extends SimpleChannelInboundHandler<HttpObject> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
            if (!(msg instanceof HttpRequest)) {
                return;
            }

            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.copiedBuffer("hello world", Charset.forName("UTF-8")));

            response.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN);
            response.headers().add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            ctx.writeAndFlush(response);
            System.out.println("read");
        }


        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            log.info("channelRegistered");
            super.channelRegistered(ctx);
        }


        @Override
        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            log.info("channelUnregistered");
            super.channelUnregistered(ctx);
        }


        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("channelActive");
            super.channelActive(ctx);
        }


        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            log.info("channelInactive");
            super.channelInactive(ctx);
        }


        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            log.info("channelReadComplete");
            super.channelReadComplete(ctx);
        }


        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            log.info("handlerAdded");
            super.handlerAdded(ctx);
        }


        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            log.info("handlerRemoved");
            super.handlerRemoved(ctx);
        }
    }
}
