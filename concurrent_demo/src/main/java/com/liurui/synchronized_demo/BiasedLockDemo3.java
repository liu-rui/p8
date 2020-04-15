package com.liurui.synchronized_demo;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author liu-rui
 * @date 2020/4/15 下午2:06
 * @description 验证：
 * 当偏向撤销超过阈值BiasedLockingBulkRevokeThreshold（默认40时），新创建的对象将不再设置偏向锁(101)，而是无锁状态(000)
 * <p>
 * 首先设置参数：-XX:BiasedLockingStartupDelay=0,关闭偏向锁加载延迟，方便做实验。
 * @since
 */
@Slf4j(topic = "BiasedLockDemo3")
public class BiasedLockDemo3 {
    public static void main(String[] args) throws InterruptedException {
        BiasedLockDemo2.MyLock[] locks = new BiasedLockDemo2.MyLock[50];

        //初始化，默认是偏向锁，但没有偏向特定的线程
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new BiasedLockDemo2.MyLock();
            log.info("{} {}", i, ClassLayout.parseInstance(locks[i]).toPrintable());
        }

        Thread t1 = new Thread(() -> {
            //使得所有的锁偏向T1线程，mark word中记录了当前线程ID
            for (int i = 0; i < locks.length; i++) {
                synchronized (locks[i]) {
                    log.info("{} {}", i, ClassLayout.parseInstance(locks[i]).toPrintable());
                }
                log.info("{} {}", i, ClassLayout.parseInstance(locks[i]).toPrintable());
            }

            synchronized (BiasedLockDemo2.MyLock.class) {
                BiasedLockDemo2.MyLock.class.notifyAll();
            }
        }, "t1");

        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (BiasedLockDemo2.MyLock.class) {
                try {
                    BiasedLockDemo2.MyLock.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //索引阈值-1（因为从0开始）之前的记录，同步的时候使用了轻量级锁，同步后变成了无锁状态
            //之后的记录使用了偏向锁，记录了T2的线程ID，同步后仍然保持偏向锁和T2线程Id
            for (int i = 0; i < locks.length; i++) {
                synchronized (locks[i]) {
                    log.info("{} {}", i, ClassLayout.parseInstance(locks[i]).toPrintable());
                }
                log.info("{} {}", i, ClassLayout.parseInstance(locks[i]).toPrintable());
            }

            synchronized (BiasedLockDemo2.class) {
                BiasedLockDemo2.class.notifyAll();
            }
        }, "t2");

        t2.start();


        Thread t3 = new Thread(() -> {
            synchronized (BiasedLockDemo2.class) {
                try {
                    BiasedLockDemo2.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //索引阈值-1（因为从0开始）之前的记录，同步的时候使用了轻量级锁，同步后变成了无锁状态
            //之后的记录使用了偏向锁，记录了T2的线程ID，同步后仍然保持偏向锁和T2线程Id
            for (int i = 0; i < locks.length; i++) {
                synchronized (locks[i]) {
                    log.info("{} {}", i, ClassLayout.parseInstance(locks[i]).toPrintable());
                }
                log.info("{} {}", i, ClassLayout.parseInstance(locks[i]).toPrintable());
            }
        }, "t3");

        t3.start();
        t3.join();

        log.info(ClassLayout.parseInstance(new MyLock()).toPrintable());
        log.info(ClassLayout.parseInstance(new BiasedLockDemo3()).toPrintable());
        log.info(ClassLayout.parseInstance(new MyLock()).toPrintable());

    }

    static class MyLock {


    }
}
