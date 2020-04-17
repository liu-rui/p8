package com.liurui.unpark_demo;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author liu-rui
 * @date 2020/4/17 下午5:16
 * @description
 * @since 使用unpark 唤醒特定的线程
 * T1和T2运行后等待，过2s后唤醒T2,过2s后唤醒T1
 * <p>
 * 结论：
 * 1. park和unpark是针对线程的操作
 * 2. unpark能释放指定的线程
 * 3. 对象头中的mark word state与park和unpark没有一毛关系
 */
@Slf4j(topic = "Demo1")
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("开始等待");
            LockSupport.park();
            log.info("继续运行");
        }, "t1");
        t1.start();

        Thread t2 = new Thread(() -> {
            log.info("开始等待");
            LockSupport.park();
            log.info("继续运行");
        }, "t2");
        t2.start();


        TimeUnit.SECONDS.sleep(2);
        log.info(ClassLayout.parseInstance(t1).toPrintable());
        log.info(ClassLayout.parseInstance(t2).toPrintable());
        LockSupport.unpark(t2);

        TimeUnit.SECONDS.sleep(1);
        LockSupport.unpark(t1);
    }
}
