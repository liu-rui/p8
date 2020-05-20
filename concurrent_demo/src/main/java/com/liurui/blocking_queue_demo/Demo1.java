package com.liurui.blocking_queue_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/5/20 下午3:22
 * @description
 * @since
 */
@Slf4j
public class Demo1 {
    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(2);

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    final String goods = String.format("producer%s-%s", finalI, j);
                    log.info("生产 {}", goods);
                    try {
                        queue.put(goods);
                    } catch (InterruptedException e) {
                    }
                }
            }, "producer" + i).start();
        }


        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }

                final String goods;
                try {
                    goods = queue.take();
                    log.info("消费 {}", goods);
                } catch (InterruptedException e) {
                }
            }
        }, "consumer").start();

    }
}
