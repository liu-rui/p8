package com.liurui.blocking_queue_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author liu-rui
 * @date 2020/5/21 下午1:58
 * @description
 * 实践ArrayBlockingQueue
 * @since
 */
@Slf4j
public class Demo11 {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

        queue.put("1");
        queue.put("2");

        String s = queue.take();
        s = queue.take();

        log.info("d");


//        for (int i = 0; i < 1; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                for (int j = 0; j < 10; j++) {
//                    final String goods = String.format("producer%s-%s", finalI, j);
//                    log.info("生产 {}", goods);
//                    try {
//                        queue.put(goods);
//                    } catch (InterruptedException e) {
//                    }
//                }
//            }, "producer" + i).start();
//        }
//
//
//        new Thread(() -> {
//            while (true) {
//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                }
//
//                final String goods;
//                try {
//                    goods = queue.take();
//                    log.info("消费 {}", goods);
//                } catch (InterruptedException e) {
//                }
//            }
//        }, "consumer").start();
    }
}
