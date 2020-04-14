package com.liurui.synchronized_demo;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * 测试偏向锁
 * 通过-XX:+PrintFlagsFinal得到偏向锁默认配置：
 * BiasedLockingBulkRebiasThreshold  20
 * BiasedLockingBulkRevokeThreshold  40
 * BiasedLockingDecayTime  25000
 * BiasedLockingStartupDelay 4000  ：默认有4秒延迟
 *
 */
public class BiasedLockDemo {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Dog dog = new Dog();
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());

//        synchronized (dog){
//            System.out.println(ClassLayout.parseInstance(dog).toPrintable());
//        }
//
//        System.out.println(ClassLayout.parseInstance(dog).toPrintable());

    }

    public static class Dog {

    }
}
