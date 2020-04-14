package com.liurui.synchronized_demo;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/4/14 下午2:02
 * @description
 * @since
 */
public class Demo1 {
    static Demo1 obj = new Demo1();

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        System.out.println("---------------------");

        synchronized (obj){
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
