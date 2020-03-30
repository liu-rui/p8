package com.liurui.byte_code.byte_demo;

/**
 * 通过字节码分析操作数栈的信息（指令和栈最大深度）
 */
public class stack_demo {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int c = 40;
        int d = 50;
        int e = (a + b) * c + d;

        System.out.println(e);
    }
}
