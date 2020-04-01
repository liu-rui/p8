package com.liurui.memory_data_demo.meta_space_out_of_memory_error_demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 目标：元空间溢出
 * -XX:MaxMetaspaceSize=20m
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * -XX:+PrintFlagsInitial
 *
 * 将元空间调大是为了使用jstat看效果
 * 通过jstat -gcutil pid 观察
 *
 */
public class app {


    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(app.class);
        enhancer.setUseCache(false);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return proxy.invokeSuper(obj, args);
            }
        });
        while (true) {
            enhancer.create();
        }
    }
}
