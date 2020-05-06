package com.liurui.immutableDemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * SimpleDateFormat线程不安全问题
 */
public class Demo1 {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                final Date parse;
                try {
                    parse = simpleDateFormat.parse("2020-11-10");
                    System.out.println(parse);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
