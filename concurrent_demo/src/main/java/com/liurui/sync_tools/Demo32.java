package com.liurui.sync_tools;

import java.util.concurrent.Semaphore;

/**
 * @author liu-rui
 * @date 2020/5/19 下午2:33
 * @description 分析Semaphore源码
 * @since
 */
public class Demo32 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        new Thread(() -> {
            try {
                semaphore.acquire(); //此处加断点

                try {

                } finally {
                    semaphore.release();//此处加断点
                }
            } catch (InterruptedException e) {
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                semaphore.acquire();  //此处加断点

                try{

                }finally {
                    semaphore.release();//此处加断点
                }
            } catch (InterruptedException e) {
            }
        }, "t2").start();


        new Thread(() -> {
            try {
                semaphore.acquire();  //此处加断点

                try{

                }finally {
                    semaphore.release();//此处加断点
                }
            } catch (InterruptedException e) {
            }
        }, "t3").start();
    }
}
