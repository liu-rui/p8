package com.liurui.memory_data_demo.heap_out_of_memory_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 通过jvisualvm分析堆溢出
 * 1. 配置vm,-Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
 */
public class app {
    public static void main(String[] args) {
        List<app>  list = new ArrayList<>();

        while (true){
            list.add(new app());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
