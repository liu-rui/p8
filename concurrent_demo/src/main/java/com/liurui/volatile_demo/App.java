package com.liurui.volatile_demo;

import java.util.concurrent.TimeUnit;

/**
 * @author liu-rui
 * @date 2020/2/27 下午2:10
 * @description
 * 通过-XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly 能看出volatile对应的汇编
 * @since
 */
public class App {
    static volatile boolean stopped = false;

    public static void main(String[] args) {
        Thread worker = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stopped) {
                }
                System.out.println("worker stopped");
            }
        });
        worker.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send a stop sign");
        stopped = true;
    }
}
