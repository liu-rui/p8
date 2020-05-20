package com.liurui.sync_tools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/5/20 上午11:19
 * @description 实践CyclicBarrier
 * @since
 */
@Slf4j
public class Demo51 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            log.info("finish begin!!!");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
            }
            log.info("finish end!!!");
        });

        for (int i = 0; i < 2; i++) {

            int finalI = i;
            new Thread(() -> {
                log.info("begin..");

                try {
                    if (finalI == 0) {
                        TimeUnit.SECONDS.sleep(3);
                    }
                    log.info("await ret:{}", cyclicBarrier.await());
                } catch (InterruptedException e) {
                } catch (BrokenBarrierException e) {
                }
                log.info("end");
            }, "t" + i).start();
        }
    }
}
