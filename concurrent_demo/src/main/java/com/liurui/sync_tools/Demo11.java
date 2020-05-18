package com.liurui.sync_tools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 实践ReentrantReadWriteLock
 * 读-读可并发
 * 读-写互斥
 * 写-写互斥
 */
@Slf4j
public class Demo11 {
    public static void main(String[] args) {
        log.info("读-读测试");
        for (int i = 0; i < 2; i++) {
            DataMap dataMap = new DataMap();
            new Thread(() -> {
                dataMap.read();
            }, "t" + i).start();
        }
        log.info("读-写测试");
        {
            DataMap dataMap = new DataMap();
            new Thread(() -> {
                dataMap.read();
            }, "t-read").start();
            new Thread(() -> {
                dataMap.write();
            }, "t-write").start();
        }
        log.info("写-写测试");
        for (int i = 0; i < 2; i++) {
            DataMap dataMap = new DataMap();
            new Thread(() -> {
                dataMap.write();
            }, "t" + i).start();
        }

    }

    static class DataMap {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


        void read() {
            lock.readLock().lock();

            try {
                log.info("read");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }

        void write() {
            lock.writeLock().lock();

            try {
                log.info("write");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

}
