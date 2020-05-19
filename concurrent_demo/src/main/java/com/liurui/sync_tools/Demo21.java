package com.liurui.sync_tools;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * 实践StampedLock 乐观锁
 *1. 读-读  并发
 * 2. 读-写 互斥
 * 3. 写-写 互斥
 */
public class Demo21 {
    public static void main(String[] args) {
        StampedLock  lock= new StampedLock();

        new  Thread(()->{
            final long stamp = lock.writeLock();

            try{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
            }finally {
                lock.unlockWrite(stamp);
            }


        } , "t1").start();


        new  Thread(()->{

        } , "t1").start();
    }
}
