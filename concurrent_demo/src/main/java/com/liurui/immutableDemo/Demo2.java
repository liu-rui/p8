package com.liurui.immutableDemo;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * DateTimeFormatter 线程安全
 */
public class Demo2 {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                final TemporalAccessor parse = dateTimeFormatter.parse("2020-11-10");
                System.out.println(parse);
            }).start();
        }
    }
}
