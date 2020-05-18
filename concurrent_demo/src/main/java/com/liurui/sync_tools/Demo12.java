package com.liurui.sync_tools;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author liu-rui
 * @date 2020/5/18 下午4:00
 * @description
 * 实践ReentrantReadWriteLock的read可并发
 * @since
 */
public class Demo12 {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        new Thread(() -> {
            lock.readLock().lock();

            try {
//                try {
//                    TimeUnit.HOURS.sleep(1);
//                } catch (InterruptedException e) {
//                }
            } finally {
                lock.readLock().unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.readLock().lock(); //此处添加断点

            try {

            } finally {
                lock.readLock().unlock();
            }
        }, "t2").start();
    }
}
