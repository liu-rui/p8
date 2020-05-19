package com.liurui.sync_tools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/5/19 下午5:19
 * @description 源码分析CountDownLatch
 * @since
 */
@Slf4j
public class Demo43 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            log.info("do...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            latch.countDown();   //加断点
        }, "t1").start();

        new Thread(() -> {
            try {
                latch.await();  //加断点
            } catch (InterruptedException e) {
            }
            log.info("do...");
        }, "t11").start();

        new Thread(() -> {
            try {
                latch.await();  //加断点
            } catch (InterruptedException e) {
            }
            log.info("do...");
        }, "t12").start();
    }
}
