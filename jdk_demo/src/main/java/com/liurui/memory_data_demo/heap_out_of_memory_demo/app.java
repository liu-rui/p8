package com.liurui.memory_data_demo.heap_out_of_memory_demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过jvisualvm分析堆溢出
 * 1. 配置vm,-Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
 */
public class app {
    public static void main(String[] args) {
        List<app>  list = new ArrayList<>();

        while (true){
            list.add(new app());
            System.gc();

            if(list  == null){
                break;
            }
        }

        System.out.println(list.size());
    }
}
