package com.liurui.class_loader_demo;

/**
 * @author liu-rui
 * @date 2020/3/4 下午5:19
 * @description
 * @since
 */
public class launcher_demo {
    public static void main(String[] args) {
        System.out.println("sun.boot.class.path    " + System.getProperty("sun.boot.class.path"));
        System.out.println("java.ext.dirs   " + System.getProperty("java.ext.dirs"));
        System.out.println("java.class.path  " + System.getProperty("java.class.path"));
    }
}
