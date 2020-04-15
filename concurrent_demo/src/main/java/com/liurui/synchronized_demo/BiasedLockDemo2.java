package com.liurui.synchronized_demo;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author liu-rui
 * @date 2020/4/15 上午11:22
 * @description
 * @since 测试批量重定向
 * 当批量撤销阈值没有到达BiasedLockingBulkRebiasThreshold（默认20）设置的值时，会由偏向锁转为公平锁；达到的话，此时会重新使用偏向锁并记录当前线程。
 * <p>
 * 目标：验证上面的结论
 * 配置添加如下参数：-XX:BiasedLockingStartupDelay=0 关闭偏向锁启动延迟
 * <p>
 * 第一次运行：不配置BiasedLockingBulkRebiasThreshold，测试下是否在T2线程索引19开始，使用偏向锁指向T2线程
 * 第二次运行：-XX:BiasedLockingBulkRebiasThreshold=25，测试下是否在T2线程索引24开始，使用偏向锁指向T2线程
 * <p>
 * <p>
 * 结论：
 * 1. 偏向锁批量取消达到特定阈值前，会采用轻量级锁，达到后使用偏向锁来优化轻量级锁性能
 */
@Slf4j(topic = "BiasedLockDemo2")
public class BiasedLockDemo2 {
    public static void main(String[] args) throws InterruptedException {
        MyLock[] locks = new MyLock[30];

        //初始化，默认是偏向锁，但没有偏向特定的线程
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new MyLock();
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

            synchronized (MyLock.class) {
                MyLock.class.notifyAll();
            }
        }, "t1");

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MyLock.class) {
                    try {
                        MyLock.class.wait();
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
            }
        }, "t2");

        t2.start();
        t2.join();
    }

    static class MyLock {

    }
}
