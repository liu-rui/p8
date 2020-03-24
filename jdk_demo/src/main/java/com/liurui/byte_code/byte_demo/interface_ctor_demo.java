package com.liurui.byte_code.byte_demo;

/**
 * 通过cclasslib分析结果：
 * 1. 常量如果声明的是常量值的话，通过字段的属性（ConstantValue）标注
 * 2. 常量如果声明非常量值，可能通过new关键字创建的对象，那么统一插入<clinit>完成初始化。
 * 3.<clint>完成静态字段（常量或变量）的初始化工作，不仅类会有，接口也会有。
 */
public interface interface_ctor_demo {
    String a = "a";

//    Thread t = new Thread();
}
