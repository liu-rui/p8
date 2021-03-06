package com.liurui.class_loader_demo.namespace_demo;

import com.liurui.class_loader_demo.MyClassLoader;

/**
 * @author liu-rui
 * @date 2020/3/13 上午11:33
 * @description
 * 命名空间是由类加载器和类名称（binary name）组成
 * 相同的类名称可能会有不同的命名空间
 * @since
 */
public class app {
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader1 = new MyClassLoader("/Users/123hhh/mine");
        MyClassLoader classLoader2 = new MyClassLoader("/Users/123hhh/mine");

        final Class<?> car1Class = classLoader1.loadClass("com.liurui.class_loader_demo.Car");
        final Class<?> car2Class = classLoader2.loadClass("com.liurui.class_loader_demo.Car");

        System.out.println(car1Class == car2Class);
        System.out.println(car1Class.getClassLoader());
        System.out.println(car2Class.getClassLoader());
    }
}
