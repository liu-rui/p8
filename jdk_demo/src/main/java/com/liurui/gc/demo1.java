package com.liurui.gc;

import java.util.concurrent.TimeUnit;

/**
 * 目标：分析GC日志
 * <p>
 * -verbose:gc
 * -Xms20m
 * -Xmx20m
 * -Xmn10m
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 */
public class demo1 {
    public static void main(String[] args) {
        int len = 1024 * 1024;
        while (true) {

            byte[] a = new byte[4 * len];

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
