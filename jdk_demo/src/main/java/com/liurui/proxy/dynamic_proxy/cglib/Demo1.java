package com.liurui.proxy.dynamic_proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liu-rui
 * @date 2020/3/2 下午5:46
 * @description 结论：
 * cglib会代理
 * 1. 代理object类中的hashcode, tostring, equals
 * 2. 代理父类的非final方法
 * 3. 代理接口中的所有的方法
 * @since
 */
public class Demo1 {
    public static class Customer {
        public void add(String name) {
            System.out.printf("hello %s\n", name);
        }

        @Override
        public int hashCode() {
            System.out.println("costmer hashcode");
            return super.hashCode();
        }


        @Override
        public boolean equals(Object obj) {
            System.out.println("customer equals");
            return super.equals(obj);
        }

        @Override
        public String toString() {
            System.out.println("customer toString");
            return "cutsomer to String";
        }

        public final void finalMethod() {
            System.out.println("customer  findMethod");
        }

        void write() {
            System.out.println("customer write");
        }
    }

    public static class MyMethodInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("---------");
            System.out.println("before");
            final Object ret = proxy.invokeSuper(obj, args);
            System.out.println("after\n");
            return ret;
        }
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "cglib_tmp");

        //创建一个父类为Customer实现了Runnable接口，方法拦截器为MyMethodInterceptor的代理对象
        final Customer proxy = (Customer) Enhancer.create(Customer.class,
                new Class[]{Runnable.class}, //
                new MyMethodInterceptor());

        proxy.add("六二");
        proxy.hashCode();
        proxy.toString();
        proxy.equals(null);
        System.out.println(proxy.getClass());
        proxy.finalMethod(); //final方法无法代理
        proxy.write();

        try {
            final Runnable runnable = (Runnable) proxy;
            runnable.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
