package com.liurui.memory_data_demo.stack_overflow_error_demo;

/**
 * 测试虚拟机栈溢出,最小为160k
 * 1. 设置jvm参数, -Xss160k
 */
public class app {
    static  int count = 0;

    static  void test(){
        count++;
        try {
            //为了便于jvisualvm或者jconsole工具进行分析
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test();
    }

    public static void main(String[] args) {
        try {
            test();

        }catch (Throwable e){
            System.out.println(count);
            e.printStackTrace();
        }
    }
}
