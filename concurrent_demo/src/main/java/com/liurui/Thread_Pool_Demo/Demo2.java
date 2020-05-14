package com.liurui.Thread_Pool_Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liu-rui
 * @date 2020/5/8 上午11:42
 * @description
 * @since
 */
@Slf4j
public class Demo2 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                2,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(20));
        for (int i = 0; i < 10; i++) {
            final String o;
            try {
                o = threadPoolExecutor.invokeAny(Arrays.asList(() -> {
                            AtomicReference<String> a = new AtomicReference<>();
                            final Thread thread = new Thread(() -> {
                                int j = 0;
                                try {
                                    try {
                                        final int seconds = ThreadLocalRandom.current().nextInt(10);

                                        log.info("{}秒", seconds);
                                        TimeUnit.SECONDS.sleep(seconds);
                                    } finally {
                                        log.info("close");
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                a.set("10");
                            }, "t1");
                            try {
                                thread.start();
                                thread.join(4000);
                                return a.get();
                            } finally {
                                log.info("finally");
                                thread.stop();
                            }
                        },
                        () -> {
                            final int seconds = ThreadLocalRandom.current().nextInt(10);
                            log.info("{}秒", seconds);
                            TimeUnit.SECONDS.sleep(seconds);
                            return "ok";
                        }), 3, TimeUnit.SECONDS);

                log.info("{}－> {}", i, o);
            } catch (Exception ex) {
                log.error(i + "出现异常", ex);
            }
        }


//        for (Future<String> future : futures) {
//            try {
//                String ret =  future.get(2,TimeUnit.SECONDS);
//                log.info("--------------{}",ret);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }
}
