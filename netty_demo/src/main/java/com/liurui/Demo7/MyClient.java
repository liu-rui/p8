package com.liurui.Demo7;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class MyClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientChannelInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8912).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }
    }

    static class MyClientChannelInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender())
                    .addLast(new ProtobufDecoder(CustomerProto.Customer.getDefaultInstance()))
                    .addLast(new ProtobufVarint32FrameDecoder())
                    .addLast(new ProtobufEncoder())
                    .addLast(new MyClientHandler());
        }
    }

    static class MyClientHandler extends SimpleChannelInboundHandler<CustomerProto.Customer> {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            ctx.channel().writeAndFlush(CustomerProto.Customer.newBuilder()
                    .setName("ren")
                    .setAge(20)
                    .build());
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, CustomerProto.Customer msg) throws Exception {

        }


    }
}
