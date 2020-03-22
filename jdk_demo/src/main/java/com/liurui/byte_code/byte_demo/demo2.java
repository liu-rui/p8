package com.liurui.byte_code.byte_demo;

import com.liurui.class_loader_demo.singlon_demo.app;

/**
 * 通过观察jclasslib的clinit方法的code结构体，b=0操作属于初始值操作
 */

public class demo2 {
    public static int a = 1;

    private static demo2 singlon = new demo2();

    private demo2() {
        a++;
        b++;
        System.out.println(a);
        System.out.println(b);
    }

    public static int b = 0;
}
