package com.liurui.proxy.dynamic_proxy.jdk;

import com.liurui.proxy.UserService;
import com.liurui.proxy.UserServiceImpl;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author liu-rui
 * @date 2020/3/2 上午10:05
 * @description
 * @since
 */
public class app {
    public static void main(String[] args) throws IOException {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        UserService target = new UserServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(target);
        UserService proxy = (UserService) Proxy.newProxyInstance(app.class.getClassLoader(),
                target.getClass().getInterfaces(),
                handler);

        proxy = (UserService) Proxy.newProxyInstance(app.class.getClassLoader(),
                target.getClass().getInterfaces(),
                handler);

        proxy.add("李四");
        System.out.printf("调用hashcode 目标对象：%s  代理对象：%s\n", target.hashCode(), proxy.hashCode());
    }
}
