package com.liurui.sync_tools;

import lombok.extern.slf4j.Slf4j;
import sun.java2d.opengl.OGLContext;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author liu-rui
 * @date 2020/5/19 下午3:47
 * @description 模拟这样的场景：
 * w1,w2是写操作;r1,r2是读操作
 * w1获取到锁后不释放，同时按照顺序获取锁,r1,w2,r2
 * 验证下，当w1释放写锁后,r1会被唤醒获取到读锁是否会唤醒读锁r2，因为都是读锁可并行执行
 * <p>
 * 结论：
 * r1可以获取到读取，但不会唤醒r2，因为r1只会唤醒后继是读锁的线程。
 * @since
 */
@Slf4j
public class Demo14 {
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

        new Thread(() -> {
            lock.readLock().lock();

            try {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
                log.info("r1");
            } finally {
                lock.readLock().unlock();
            }
        }, "r1").start();

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
        }, "w2").start();

        new Thread(() -> {
            lock.readLock().lock();

            try {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
                log.info("r2");
            } finally {
                lock.readLock().unlock();
            }
        }, "r2").start();
    }
}
