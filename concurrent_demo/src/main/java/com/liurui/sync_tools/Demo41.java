package com.liurui.sync_tools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 实现CountDownLatch
 * 2个线程执行完后，特定线程打印
 */
@Slf4j
public class Demo41 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,
                4,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10));

        for (int i = 0; i < 2; i++) {
            executor.submit(() -> {
                log.info("doing");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
                latch.countDown();
            });
        }


        executor.submit(() -> {
            try {
                latch.await();
                log.info("finish");
            } catch (InterruptedException e) {
            }
        });
    }
}
