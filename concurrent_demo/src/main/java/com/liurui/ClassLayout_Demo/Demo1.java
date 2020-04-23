package com.liurui.ClassLayout_Demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * 1. 对象布局
 * 2. 数组对象布局
 * 3. 成员布局
 * 4.
 */
@Slf4j
public class Demo1 {

    @Getter
    @AllArgsConstructor
    static class Customer {
        private int id;
        private Order order;
    }

    @Getter
    @AllArgsConstructor
    static class Order {
        private String orderId;
        private double price;
    }

    public static void main(String[] args) {
        log.info(VM.current().details());
        //对象布局
        log.info(ClassLayout.parseInstance(new Customer(1, new Order("100", 34))).toPrintable());

        //数组布局
    }
}
