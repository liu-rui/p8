package com.liurui.proxy.dynamic_proxy.jdk;

import sun.misc.ProxyGenerator;
import sun.misc.Unsafe;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liu-rui
 * @date 2020/3/31 下午5:31
 * @description
 * @since
 */
public class Proxy_generator_Demo {
    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ProxyGenerator.generateProxyClass("a.b.c.d" , new Class<?>[]{ Runnable.class });


    }
}
