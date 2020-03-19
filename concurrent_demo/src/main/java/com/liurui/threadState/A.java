package com.liurui.threadState;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/2/26 下午2:23
 * @description
 * @since
 */
public class A {
    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();


        Thread a = new Thread(() -> {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    queue.put(i++);
                } catch (InterruptedException e) {
                    System.out.println("error a return");
                    return;
                }
            }
        });

        a.start();

        Thread b = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    System.out.println("error b return");
                    return;
                }
            }
        });

        b.start();
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
        }

        a.interrupt();
        b.interrupt();

    }
}
