package com.liurui.proxy.dynamic_proxy.jdk;

import com.liurui.proxy.UserService;
import com.liurui.proxy.UserServiceImpl;
import sun.misc.ProxyGenerator;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author liu-rui
 * @date 2020/3/2 上午10:05
 * @description
 * @since
 */
public class app {
    public static void main(String[] args) throws IOException {
        UserService target = new UserServiceImpl();
        MyInvocationHandler  handler = new MyInvocationHandler(target);
        final UserService proxy = (UserService) Proxy.newProxyInstance(app.class.getClassLoader(),
                target.getClass().getInterfaces(),
                handler);

        proxy.add("李四");

        System.out.println(proxy.hashCode());

        final byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", target.getClass().getInterfaces());

        Files.write(Paths.get( "proxy_source.class" ), bytes);
    }
}
