package com.liurui.sync_tools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author liu-rui
 * @date 2020/5/19 下午4:17
 * @description
 * 实践写锁释放后是如何唤醒多个读锁的
 * @since
 */
@Slf4j
public class Demo15 {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        new Thread(() -> {
            lock.writeLock().lock();

            try {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
                log.info("w1");
            } finally {
                lock.writeLock().unlock();
            }
        }, "w1").start();


        for (int i = 0; i < 4; i++) {
            int finalI = i;
            new Thread(() -> {
                lock.readLock().lock();

                try {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                    }
                    log.info("r" + finalI);
                } finally {
                    lock.readLock().unlock();
                }
            }, "r" + i).start();
        }
    }
}
