package com.liurui.byte_code.byte_demo;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

/**
 * 栈帧（statck frame)
 * 栈帧是一种用于帮助虚拟机执行方法调用和方法执行的数据结构。
 * 栈帧本身是一种数据结构，封装了方法的局部变量表、动态链接信息、方法的返回地址以及操作数栈等信息。
 * 符号引用和直接引用
 * 有些符号引用是在类加载阶段或是第一次使用时就会转换为直接引用，这种转换叫做静态引用；另外一些符号引用则是在每次运行期转换为直接引用，这种转换叫做动态链接，这体现为java的多态性。
 * 调用方法的指令助记符：
 * 1. invokeinterface:调用接口中的方法，实际上是在运行期决定的，决定到底调用实现该接口的哪个对象的特定方法。
 * 2. invokestatic: 调用静态方法
 * 3. invokespecial:调用自己的私有方法，构造方法(<init>)以及父类的方法。
 * 4. invokevirtual:调用虚方法，运行期动态查找的过程。
 * 5. invokedynamic：动态调用方法。JDK1.7引入。
 */
public class Invoke_Method_Demo {

    public static  void test(){

    }

    public static void main(String[] args) {
        test();
    }
}
