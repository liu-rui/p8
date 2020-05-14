package com.liurui.Thread_Pool_Demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author liu-rui
 * @date 2020/5/8 下午12:12
 * @description
 * @since
 */
@Slf4j
public class Demo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> {
            int j = 0;
//            while (true) {
//                log.info("{}", j++);
//            }
            return "aa";
        });

        final CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> {
//            int j = 0;
//            while (true) {
//                log.info("{}", j++);
//            }
            return "bb";
        });


        final CompletableFuture voidCompletableFuture = CompletableFuture.allOf(a, b);

        log.info("{}",voidCompletableFuture.get());

    }
}
