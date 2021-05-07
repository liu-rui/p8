package com.liurui.class_loader_demo.unload_class_demo;

import com.liurui.class_loader_demo.MyClassLoader;

import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/3/13 上午11:48
 * @description
 * -XX:+TraceClassLoading -XX:+TraceClassUnloading
 *
 * @since
 */
public class app {
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("/Users/123hhh/mine");
        Class<?> carClass = classLoader.loadClass("com.liurui.class_loader_demo.Car");
        Object instance = carClass.newInstance();

        TimeUnit.SECONDS.sleep(30);
        System.out.println("回收资源");

        //释放引用，使得虚拟机不可及，这样GC会回收方法区的类定义
        classLoader = null;
        carClass = null;
        instance = null;
        System.gc();
        TimeUnit.SECONDS.sleep(30);
    }
}
