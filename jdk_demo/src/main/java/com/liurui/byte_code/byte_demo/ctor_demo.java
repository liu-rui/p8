package com.liurui.byte_code.byte_demo;


/**
 * 通过字节码文件分析实例字段初始化过程
 * 借助idea插件jclasslib
 *
 * 结论：
 * 实例对象的初始化过程是通过执行<init>方法完成的
 * 当未提供默认构造函数的时候系统会自动生成一个<init>方法
 * 提供了几个构造函数，就会生成几个<init>方法
 * 字段声明赋值的统一插入到提供的构造函数代码的上面，与在构造函数的前后无关(这一点和静态字段的不同)
 * 有几个构造函数，字段声明赋值就会被插入几次。
 * 构造函数（无论是否有参数）始终首先执行父类的无参数构造函数(ctor_parent_demo运行后确实是这样)
 * 实例常量有些不同（和静态常量处理方式相同）：
 * 1. 声明时赋值的，通过字段的属性（ConstantValue）标注
 * 2. 代码块中赋值的，还是通过代码块中执行
 */
public class ctor_demo {
    private  int a = 10;
    private  String b= "10";
    private  final  float d  = 40;
    private  final  double e;

    public ctor_demo() {
        System.out.println("ctor0");
        e = 50;
    }



    public ctor_demo(int w) {
        this.a = w;
        System.out.println("ctor1");
        e = 60;
    }


    private  float c = 40;



}
