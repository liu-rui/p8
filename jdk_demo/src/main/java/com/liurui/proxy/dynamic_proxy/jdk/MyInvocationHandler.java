package com.liurui.proxy.dynamic_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liu-rui
 * @date 2020/3/2 上午10:09
 * @description
 * @since
 */
public class MyInvocationHandler implements InvocationHandler {
    /**
     * 被代理的对象
     */
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("准备向数据库中插入数据");
        final Object ret = method.invoke(target, args);
        System.out.println("插入数据库成功");
        return ret;
    }
}
