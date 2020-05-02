package com.liurui.Demo8;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

/**
 * 实践DirectByteBuffer
 *
 * 由于DirectByteBuffer是直接字节缓冲区，数据并不是分配在堆上的，而是分配在直接内存上，所以array未使用。
 *
 *
 */
@Slf4j
public class Demo5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6);

        log.info("hasArray: {}",  byteBuffer.hasArray());

        byteBuffer.putChar('A');
        byteBuffer.putInt(45);

        log.info(byteBuffer.toString());

        byteBuffer.flip();


        while (byteBuffer.hasRemaining()){
            log.info("{}" , byteBuffer.get());
        }

    }
}
