package com.liurui.byte_code.byte_demo;

/**
 * @author liu-rui
 * @date 2020/3/27 下午3:39
 * @description
 * 分析内部类字节码结构
 * 1. 内部类会生成单独的文件，文件名为inner_class_demo$MyInnnerClass
 * 2. 通过Attribute类型为innerClasses将外部类和内部类关联起来
 * @since
 */
public class inner_class_demo {
    private int a;

    public static class MyInnnerClass {
        private String str;
    }
}
