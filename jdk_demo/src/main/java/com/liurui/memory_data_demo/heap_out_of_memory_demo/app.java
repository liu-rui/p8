package com.liurui.memory_data_demo.heap_out_of_memory_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 通过jvisualvm分析堆溢出
 * 1. 配置vm,-Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./a/
 */
public class app {
    private  int a = new Random().nextInt();

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
