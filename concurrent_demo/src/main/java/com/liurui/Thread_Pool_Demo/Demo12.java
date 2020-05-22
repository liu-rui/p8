package com.liurui.Thread_Pool_Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/5/22 下午5:53
 * @description 从源码可以看到，任务执行出现异常直接往外抛，这样会造成worker死掉
 * 验证线程池，当执行出现异常是否重新创建线程继续处理任务
 * <p>
 * <p>
 * 结论：
 * 会创建一个新线程处理下一个任务
 * @since
 */
@Slf4j
public class Demo12 {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                1,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));

        executor.execute(() -> {
            log.info("task1 begin...");
            throw new RuntimeException("task1 error");
        });

        executor.execute(() -> {
            log.info("task2 begin...");
            log.info("task2 end...");
        });

        TimeUnit.SECONDS.sleep(5);
        log.info("{}", executor.getTaskCount());
        log.info("{}", executor.getCompletedTaskCount());
    }
}
