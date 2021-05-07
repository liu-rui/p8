package com.liurui.byte_code.byte_demo;

/**
 * @author liu-rui
 * @date 2020/3/27 上午11:29
 * @description get方法返回的结果是default, 通过分析字节码，验证下原理
 * 结论：
 * 1. finally中的字节码会插入到return字节码前和catch中
 * 2. 如果存在finally语句，作为返回结果的变量会使用单独的局部变量（局部变量表中不存在的索引为1的变量）存储并作为return返回。
 * @since
 */
public class finally_demo {

    static String get() {
        String a = "default";

        try {
            System.out.println("exec code");
            return a;
        } finally {
            a = "finally";
            System.out.println("exec finally");
        }
    }

    public static void main(String[] args) {
        System.out.println("get value " + get());
    }
}
