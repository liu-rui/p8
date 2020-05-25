package com.liurui.blocking_queue_demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 分析CopyOnWriteArraySet源码
 * <p>
 * 内部调用了CopyOnWriteArrayList
 * 修改操作加锁，通过数组复制来扩容
 * 获取操作无锁
 * 适合于读多写少的情况
 */
@Slf4j
public class Demo32 {
    public static void main(String[] args) {
        CopyOnWriteArraySet<Integer> list = new CopyOnWriteArraySet<>();


        list.add(10);
        list.add(20);
        list.contains(4);
        list.remove(20);
    }
}
