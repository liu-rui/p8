package com.liurui.blocking_queue_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 分析CopyOnWriteArrayList源码
 * <p>
 * 修改操作加锁，通过数组复制来扩容
 * 获取操作无锁
 * 适合于读多写少的情况
 */
@Slf4j
public class Demo31 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        list.add(10);//加断点
        list.add(6);
        list.add(1, 4);//加断点
        final Integer i = list.get(1);//加断点，可以看到get操作无锁
        log.info("{}", i);
        list.remove(1);//加断点
    }
}
