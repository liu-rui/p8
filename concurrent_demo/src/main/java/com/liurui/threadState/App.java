package com.liurui.threadState;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " finished");
                }
            }
        }, "A");
        a.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + " begin");
//                    obj.notify();
                    a.interrupt();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getName() + " finished");
                }
            }
        }, "B");
        b.start();

        while (true){
            System.out.println("A:" +  a.getState() + "; B:" + b.getState() + "\n");
            Thread.sleep(1000);
        }
    }
}
