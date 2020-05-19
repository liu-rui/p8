package com.liurui.sync_tools;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @author liu-rui
 * @date 2020/5/19 下午5:08
 * @description 实践： 10个线程同时执行加载任务，加载完成后打印“加载完成，游戏开始”
 * @since
 */
public class Demo42 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        final ExecutorService pool = Executors.newFixedThreadPool(10);
        String[] tasks = new String[10];

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pool.submit(() -> {
                for (int j = 0; j <= 100; j++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100));
                    } catch (InterruptedException e) {
                    }
                    tasks[finalI] = j + "%";
                    System.out.print("\r" + Arrays.toString(tasks));
                }
                latch.countDown();
            });
        }

        try {
            latch.await();
            System.out.println("\n加载完成，游戏开始");
        } catch (InterruptedException e) {
        }
    }
}
