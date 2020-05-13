package com.liurui.Thread_Pool_Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Demo11 {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0,
                2,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));

        executor.submit(() -> {
            log.info("a");
        });
    }
}
