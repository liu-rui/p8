package com.liurui.Thread_Pool_Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Demo11 {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                1,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));

        executor.submit(() -> {
            try {
                TimeUnit.HOURS.sleep(3);
            } catch (InterruptedException e) {

            }
        });

//        executor.submit(() -> {
//            try {
//                TimeUnit.HOURS.sleep(3);
//            } catch (InterruptedException e) {
//
//            }
//        });
    }
}
