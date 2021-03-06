package com.liurui.sync_tools;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liu-rui
 * @date 2020/5/18 下午2:56
 * @description 代码跟踪ReentrantLock的Lock和Release
 * @since
 */
public class Demo2 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            try {
//                TimeUnit.HOURS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); //此处加断点，唤醒t2
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();   //此处加断点，调试锁已经被占的情况

            try {

            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
