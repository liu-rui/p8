package com.liurui.Demo8;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

/**
 * 实践IntBuffer
 * 观察 position,limit,capacity
 */
@Slf4j
public class Demo1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(7);

        //添加元素
        for (int i = 0; i < 5; i++) {
            buffer.put(i + 10);
            //Limit和Capacity相等，position自动+1
            log.info(buffer.toString());
        }
        buffer.flip();//翻转，从写变成了读
        log.info("------------------");
        log.info(buffer.toString());//limit设置为数据个数 ， position设置为0 ，  Capacity不变


        while (buffer.hasRemaining()){
            //调用get方法，position会自动+1
            log.info("值：{}  还剩：{}个"  , buffer.get() , buffer.remaining());
        }


        log.info(buffer.toString());
        buffer.rewind();//倒带，这样可以重新从开头读
        //limit设置为数据个数 ， position设置为0 ，  Capacity不变
        log.info("{} {}" , buffer.remaining() ,buffer.toString());

        //清空数据（逻辑删除），position为0，limit和capacity相同
        buffer.clear();
        log.info("{} {}" , buffer.remaining() ,buffer.toString());
    }
}
