package com.liurui.sync_tools;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import sun.rmi.runtime.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 调试源码AQS的Condition的await和signal方法执行过程
 */
@Slf4j
public class Demo3 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        //创建5个wait线程
        for(int i = 0 ;i< 5;i++) {
             new Thread(() -> {
                lock.lock();

                try {
                   log.info("睡觉");
                    condition.await();
                    log.info("睡醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }, "wait-" + i).start();
        }

        //创建一个需要线程，分别执行await和signal，添加断点，用于源码调试
        new Thread(() -> {
            lock.lock();

            try {
               condition.await();
//                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "run").start();


    }
}
