package com.liurui.CAS_Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author liu-rui
 * @date 2020/4/26 上午9:51
 * @description ABA问题
 * 问题复现：
 * t1线程获取了100，然后休息，扣减10,原子操作成功了
 * ti休息的过程中，t1获取100,执行了-100,+100，最终结果还是100
 * 由于数据没有变化，t1无法感知到
 * @since
 */
@Slf4j
public class ABA_Resove_DEMO1 {
    static AtomicStampedReference<Integer> price = new AtomicStampedReference<Integer>(100, 0);

    public static void main(String[] args) {
        new Thread(() -> {
            final Integer oldPrice = price.getReference();
            final Integer stamp = price.getStamp();
            final Integer newPrice = oldPrice - 10;

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }

            log.info("old:{} new:{} stamp:{}  ret:{}", oldPrice.toString(), newPrice.toString(), stamp, price.compareAndSet(oldPrice, newPrice, stamp, stamp + 1));
        }, "t1").start();


        new Thread(() -> {
            Integer oldPrice = price.getReference();
            Integer stamp = price.getStamp();
            Integer newPrice = oldPrice - 10;

            log.info("old:{} new:{} stamp:{}  ret:{}", oldPrice.toString(), newPrice.toString(), stamp, price.compareAndSet(oldPrice, newPrice, stamp, stamp + 1));

            oldPrice = price.getReference();
            stamp = price.getStamp();
            newPrice = oldPrice + 10;

            log.info("old:{} new:{} stamp:{}  ret:{}", oldPrice.toString(), newPrice.toString(), stamp, price.compareAndSet(oldPrice, newPrice, stamp, stamp + 1));
        }, "t2").start();
    }
}
