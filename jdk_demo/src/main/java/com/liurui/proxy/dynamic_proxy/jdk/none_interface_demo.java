package com.liurui.proxy.dynamic_proxy.jdk;

import com.liurui.proxy.UserService;
import com.liurui.proxy.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author liu-rui
 * @date 2020/3/31 下午4:42
 * @description 验证接口数据为o时创建代理对象
 * 结论：
 * 1. 可以正常执行
 * 2. equals,toString,hashCode会代理给invocationHandler来执行
 * @since
 */
public class none_interface_demo {
    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        UserService target = new UserServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(target);
        Object proxy = (Object) Proxy.newProxyInstance(app.class.getClassLoader(),
                new Class<?>[0],
                handler);

        System.out.printf("调用hashcode 目标对象：%s  代理对象：%s\n", target.hashCode(), proxy.hashCode());
    }
}
