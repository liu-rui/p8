package com.liurui.Demo6;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author liu-rui
 * @date 2020/4/28 下午2:30
 * @description
 * @since
 */
@Slf4j
public class MyWebSocketServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyWebSocketChannelInitializer());
            final ChannelFuture channelFuture = serverBootstrap.bind(8912).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    static class MyWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new HttpServerCodec())
                    .addLast(new HttpObjectAggregator(65536))
                    .addLast(new WebSocketServerProtocolHandler("/a"))
                    .addLast(new MyWebSocketHandler());
        }
    }

    static class MyWebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
        static final ChannelGroup CHANNELS = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
            if (msg instanceof TextWebSocketFrame) {
                final TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) msg;
                final TextWebSocketFrame message = new TextWebSocketFrame(String.format("%s: %s", ((InetSocketAddress) ctx.channel().remoteAddress()).getPort(), textWebSocketFrame.text()));

                for (Channel channel : CHANNELS) {
                    //当前用户发的消息
                    if (channel.equals(ctx.channel())) {
                        continue;
                    }
                    channel.writeAndFlush(message);
                }
            } else {
                log.info(msg.toString());
            }
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            CHANNELS.writeAndFlush(new TextWebSocketFrame(String.format("%s 上线了", ((InetSocketAddress) ctx.channel().remoteAddress()).getPort())));
            log.info("channelActive");
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            super.channelInactive(ctx);
            CHANNELS.writeAndFlush(new TextWebSocketFrame(String.format("%s 下线了", ((InetSocketAddress) ctx.channel().remoteAddress()).getPort())));
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            super.handlerAdded(ctx);
            CHANNELS.add(ctx.channel());
            log.info("{} 建立连接", ((InetSocketAddress) ctx.channel().remoteAddress()).getPort());
        }

        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            super.handlerRemoved(ctx);
            log.info("{} 断开连接", ((InetSocketAddress) ctx.channel().remoteAddress()).getPort());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
            cause.printStackTrace();
            ctx.channel().close();
            log.info("{} 出现异常，断开连接", ((InetSocketAddress) ctx.channel().remoteAddress()).getPort());
        }
    }
}
