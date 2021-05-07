//package com.liurui.class_loader_demo;
//
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLClassLoader;
//
///**
// * @author liu-rui
// * @date 2020/3/4 下午5:16
// * @description 同一个class文件通过不同的classloader加载时，是不同的，原因是class对应的字节码加载到jvm的地址是不同的
// * @since
// */
//public class app {
//
//
//    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
//        URLClassLoader loader1 = new URLClassLoader(new URL[]{new URL("file:///home/liurui/mine/code/java/source/p8/jdk_demo/target/classes/")},
//                null); //指定parent为null
//        String carClassStr = "com.liurui.class_loader_demo.Car";
//
//        System.out.println(loader1.getParent());
//        final Class<?> carClass = loader1.loadClass(carClassStr);
//
//        //不再相等，来源于不同的loader加载，自然内存地址不同
//        System.out.println(carClass.equals(Car.class));
//
//        System.out.println(carClass.getClassLoader().equals(loader1));
//        System.out.println(Car.class.getClassLoader().equals(ClassLoader.getSystemClassLoader()));
//        System.out.println(app.class.getClassLoader().equals(ClassLoader.getSystemClassLoader()));
//    }
//}
