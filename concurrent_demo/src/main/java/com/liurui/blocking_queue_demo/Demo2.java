package com.liurui.blocking_queue_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Spliterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liu-rui
 * @date 2020/5/21 下午12:10
 * @description 实践并发迭代器 Spliterator
 * @since
 */
@Slf4j
public class Demo2 {
    public static void main(String[] args) {
        final ArrayList<Integer> list = new ArrayList<>();
        final ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        final Spliterator<Integer> spliterator = list.spliterator();

        spliterator.forEachRemaining(w -> {
            executorService.submit(() -> {
                log.info("{}", w);
            });
        });
    }
}
