package com.liurui.immutableDemo;

/**
 * 调用final static 常量时会转变为栈操作
 * 通过修改line 15 删除final，观察line 11行字节码查看影响
 * <p>
 * 1. 添加了final
 * BIPUSH 30
 * INVOKEVIRTUAL java/io/PrintStream.println (I)V
 * GETSTATIC com/liurui/immutableDemo/Demo3$A.b : I
 * INVOKEVIRTUAL java/io/PrintStream.println (I)V
 * <p>
 * 2. 删除了final
 * GETSTATIC com/liurui/immutableDemo/Demo3$A.a : I
 * INVOKEVIRTUAL java/io/PrintStream.println (I)V
 * <p>
 * GETSTATIC com/liurui/immutableDemo/Demo3$A.b : I
 * INVOKEVIRTUAL java/io/PrintStream.println (I)V
 * <p>
 * <p>
 * 结论：
 * 1. final 修饰的值是个魔数的话，会转换为栈操作，不会引起目标class的初始化动作
 * 2. final 修饰的值是个魔数的话，会始终调用堆中对象的属性，会引起目标class的初始化动作
 */
public class Demo3 {
    public static void main(String[] args) {
        System.out.println(A.a);
        System.out.println(A.b);
    }

    static class A {
        final static int a = 30;
        final static int b = Math.max(1, 2);
    }

}
