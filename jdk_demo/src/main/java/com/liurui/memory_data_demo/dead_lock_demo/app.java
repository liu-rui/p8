package com.liurui.memory_data_demo.dead_lock_demo;

/**
 * 通过jvisualvm和jconsole检测死锁
 */
public class app {
    private static  class  MyLock{
        private  String name;

        public MyLock(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "MyLock{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    private static MyLock lock1 = new MyLock("lock1");
    private static MyLock lock2 = new MyLock("lock2");

    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (lock2) {
                        System.out.println("a ok");
                    }
                }
            }
        },"threada");

        a.start();

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1){
                        System.out.println("b ok");
                    }
                }
            }
        },"threadb");
        b.start();
    }
}
