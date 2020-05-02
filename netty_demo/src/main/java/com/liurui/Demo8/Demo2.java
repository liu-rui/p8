package com.liurui.Demo8;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;
import java.util.Arrays;

/**
 * Buffer。Slice会创建一个分片，对应的数据还是原有的buffer.
 * 对slice数据变更会反映到原有的buffer上
 */
@Slf4j
public class Demo2 {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(6);

        for (int i = 0; i < 6; i++) {
            intBuffer.put(i, i + 1);
        }

        log.info(Arrays.toString(intBuffer.array()));

        intBuffer.position(2);
        intBuffer.limit(4);

        IntBuffer slice = intBuffer.slice();

        log.info(slice.toString());
        log.info(Arrays.toString(slice.array()));

        for (int i = 0; i < slice.limit(); i++) {
            slice.put(i, slice.get(i) * 4);
        }
        log.info(Arrays.toString(slice.array()));


    }
}
