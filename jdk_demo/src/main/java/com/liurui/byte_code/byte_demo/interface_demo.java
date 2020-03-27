package com.liurui.byte_code.byte_demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liu-rui
 * @date 2020/3/27 下午3:49
 * @description 分析有多接口的类的字节码结构
 * @since
 */
public class interface_demo implements Runnable, InvocationHandler {

    @Override
    public void run() {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
