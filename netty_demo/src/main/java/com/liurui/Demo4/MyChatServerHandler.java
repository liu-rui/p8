package com.liurui.Demo4;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author liu-rui
 * @date 2020/4/27 上午9:21
 * @description
 * @since
 */
@Slf4j
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        String message = String.format("%s: %s\n",((InetSocketAddress) ctx.channel().remoteAddress()).getPort(), msg);

        for (Channel channel : channelGroup) {
            if (channel.equals(ctx.channel())) {
                continue;
            }
            channel.writeAndFlush(message);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channelGroup.writeAndFlush(String.format("%s 上线了\n", ctx.channel().remoteAddress()));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        channelGroup.writeAndFlush(String.format("%s 下线了\n", ctx.channel().remoteAddress()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        channelGroup.add(ctx.channel());
        log.info(String.format("%s成功连接！", ctx.channel().remoteAddress()));

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        log.info(String.format("%s退出连接！", ctx.channel().remoteAddress()));
    }
}
