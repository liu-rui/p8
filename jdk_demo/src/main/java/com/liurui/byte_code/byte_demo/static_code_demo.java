package com.liurui.byte_code.byte_demo;

/**
 * 总结：
 * 类的初始化时通过执行<clinit>方法完成的
 * 有且只有一个<clinit>方法
 * 按照静态变量或者静态代码块的顺序，统一都放入到<clinit>方法中
 * 静态常量有些不同：
 * 1. 声明时赋值的，通过字段的属性（ConstantValue）标注
 * 2. 代码块中赋值的，还是通过代码块中执行
 */
public class static_code_demo {
    private static  int a = 10;
    private static  String b = "b";
    private  static  final  float c = 34;
    private  static  final long e;

    static {
        a= 40;
        e=56;
    }

    private  static double d = 50;

    static {
        b= "c";
    }
}
