package com.liurui.source.demo1;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Demo1 {
    public static void main(String[] args) {
        EventLoopGroup   boss = new NioEventLoopGroup();

        log.info(boss.toString());
    }
}
