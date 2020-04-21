package com.liurui.ReentrantLock_demo;

import java.util.concurrent.locks.ReentrantLock;

//todo:
//同一间屋子,小张需要烟才能工作，小红需要巧克力才能工作。
//送外卖送来了巧克力，唤醒小红工作
//通过使用ReentrantLock的不同的条件变量，避免虚假唤醒问题。
public class Demo1 {

    public static void main(String[] args) {
        ReentrantLock room = new ReentrantLock();

        Thread  t1 = new Thread(()->{



        } , "小张");


    }
}
