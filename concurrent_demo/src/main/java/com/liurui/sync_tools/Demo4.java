package com.liurui.sync_tools;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liu-rui
 * @date 2020/5/18 下午4:03
 * @description 调试Reentrant.Condition
 * @since
 */
public class Demo4 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();

            try {
                try {
                    condition.await(); //此处加断点
                } catch (InterruptedException e) {

                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();

            try {
                condition.signal(); //唤醒
            } finally {
                lock.unlock();
            }
        }, "t1").start();
    }
}
