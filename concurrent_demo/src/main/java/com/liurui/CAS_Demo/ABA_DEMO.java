package com.liurui.CAS_Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

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
public class ABA_DEMO {
    static AtomicReference<Integer> price = new AtomicReference<Integer>(100);

    public static void main(String[] args) {
        new Thread(() -> {
            final Integer oldPrice = price.get();
            final Integer newPrice = oldPrice - 10;

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
            }

            log.info("old:{} new{} ret:{}", oldPrice.toString(), newPrice.toString(), price.compareAndSet(oldPrice, newPrice));
        }, "t1").start();


        new Thread(() -> {
            Integer oldPrice = price.get();
            Integer newPrice = oldPrice - 10;

            log.info("old:{} new{} ret:{}", oldPrice.toString(), newPrice.toString(), price.compareAndSet(oldPrice, newPrice));

            oldPrice = price.get();
            newPrice = oldPrice + 10;

            log.info("old:{} new{} ret:{}", oldPrice.toString(), newPrice.toString(), price.compareAndSet(oldPrice, newPrice));
        }, "t2").start();
    }
}
