package com.liurui.blocking_queue_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/5/25 下午3:57
 * @description TODO: 分析SynchronousQueue源码
 * @since
 */
@Slf4j
public class Demo4 {
    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    final String s = String.valueOf(i);
                    log.info("put:{}", s);
                    queue.put(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();


        new Thread(() -> {
            int i = 0;

            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("get:{}", queue.poll());
                i++;
            }
        }, "t2").start();
    }
}
