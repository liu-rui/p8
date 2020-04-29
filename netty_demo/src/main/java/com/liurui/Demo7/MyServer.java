package com.liurui.Demo7;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class MyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChannelInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8912).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    static class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder())
                    .addLast(new ProtobufDecoder(CustomerProto.Customer.getDefaultInstance()))
                    .addLast(new ProtobufEncoder())
                    .addLast(new ProtobufVarint32LengthFieldPrepender())
                    .addLast(new MyServerHandler());
        }
    }

    static class MyServerHandler extends SimpleChannelInboundHandler<CustomerProto.Customer> {

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, CustomerProto.Customer customer) throws Exception {
            System.out.println(customer);
        }
    }

}
