package com.liurui.demo3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashSet;
import java.util.Set;

public class ChatServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final Set<Channel> channels = new HashSet<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(String.format("%s%s" ,ctx.channel().remoteAddress() ,  msg));
//        ctx.writeAndFlush("回复\r\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        String message = ctx.channel().remoteAddress() + " 上线\r\n";

        for (Channel channel : channels) {
            channel.writeAndFlush(message);
        }
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        channels.remove(ctx.channel());
        String message = ctx.channel().remoteAddress() + " 下线\r\n";

        for (Channel channel : channels) {
            channel.writeAndFlush(message);
        }
    }
}
