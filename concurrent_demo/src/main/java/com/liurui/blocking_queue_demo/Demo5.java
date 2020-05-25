package com.liurui.blocking_queue_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;

/**
 * @author liu-rui
 * @date 2020/5/25 下午4:04
 * @description TODO: 源码PriorityQueue
 * @since
 */
@Slf4j
public class Demo5 {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(10);
        queue.add(20);
        queue.add(15);

        while (!queue.isEmpty()) {
            log.info("{}", queue.poll());
        }
    }
}
