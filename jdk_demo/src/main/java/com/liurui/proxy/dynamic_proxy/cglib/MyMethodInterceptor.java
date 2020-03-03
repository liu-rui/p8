package com.liurui.proxy.dynamic_proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liu-rui
 * @date 2020/3/2 下午5:48
 * @description
 * @since
 */
public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("准备向数据库中插入数据");
        final Object ret = proxy.invokeSuper(obj, args);
        System.out.println("插入数据库成功");
        return ret;
    }
}
