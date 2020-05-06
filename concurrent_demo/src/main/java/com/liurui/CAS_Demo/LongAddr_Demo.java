package com.liurui.CAS_Demo;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author liu-rui
 * @date 2020/4/27 下午2:22
 * @description LongAddr源码分析
 * 参考： https://www.cnblogs.com/tong-yuan/p/LongAdder.html
 * @since
 */
public class LongAddr_Demo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();

        longAdder.increment();

        final int ret = longAdder.intValue();

        System.out.println(ret);
        //
    }
}
