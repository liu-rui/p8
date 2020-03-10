package com.liurui.class_loader_demo.static_demo;

import java.util.UUID;

public class app {
    public static void main(String[] args) {
        //只会初始化静态变量所在的类
        //参加视频：https://www.bilibili.com/video/av75247289?p=6
        System.out.println(Child.parentStr);

    }

    static {
        System.out.println("this is app blocks");
    }


    public  static  class  Parent{
        public static  String parentStr = "this is parent string";

        static {
            System.out.println("this is parent static code block");
        }
    }

    public  static  class  Child  extends   Parent {
        public static  String childStr = UUID.randomUUID().toString();

        static {
            System.out.println("this is child static code block");
        }
    }
}
