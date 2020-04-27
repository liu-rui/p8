package com.liurui.CAS_Demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author liu-rui
 * @date 2020/4/27 上午10:40
 * @description 测试原子性
 * 100线程累加10000次
 * @since
 */
public class Demo1 {
    static int count = 0;
    static AtomicInteger num = new AtomicInteger();
    static LongAdder addr = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        e1(); //存在线程安全问题
        e2(); //使用了AtomicInteger，线程安全
        e3(); //使用了LongAddr，线程安全，高并发下性能比AtomicInteger好
    }

    static void e1() throws InterruptedException {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    ++count;
                }
            });
            threads[i].start();
        }


        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(count);
    }

    static void e2() throws InterruptedException {
        final long nanoTime = System.nanoTime();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    num.incrementAndGet();
                }
            });
            threads[i].start();
        }


        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(num.get() + "----" + (System.nanoTime() - nanoTime));
    }


    static void e3() throws InterruptedException {
        final long nanoTime = System.nanoTime();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    addr.increment();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(addr.intValue() + "----" + (System.nanoTime() - nanoTime));
    }
}
