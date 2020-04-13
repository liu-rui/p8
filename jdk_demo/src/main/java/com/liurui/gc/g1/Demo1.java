package com.liurui.gc.g1;

/**
 * 观察G1的输入日志
 * -Xms10m
 * -Xmx10m
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * -XX:+UseG1GC
 * -XX:MaxGCPauseMillis=200
 */
public class Demo1 {
    public static void main(String[] args) {
       int m=  1024  * 1024;

        byte[] a = new byte[m];
        byte[] b = new byte[m];
        byte[] c = new byte[m];
        System.out.println("hello world");
    }
}
