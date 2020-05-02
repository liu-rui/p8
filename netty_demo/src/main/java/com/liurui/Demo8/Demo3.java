package com.liurui.Demo8;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.nio.IntBuffer;
import java.util.Arrays;

/**
 * asReadOnlyBufferf方法创建一个只读的buffer，pos.limit，capacity为当前状态。
 *
 */
@Slf4j
public class Demo3 {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(6);

        for (int i = 0; i < 6; i++) {
            intBuffer.put(i, i + 1);
        }

        log.info(Arrays.toString(intBuffer.array()));

        intBuffer.position(2);
        intBuffer.limit(4);

        IntBuffer readOnlyBuffer = intBuffer.asReadOnlyBuffer();
        log.info(readOnlyBuffer.toString());
        log.info( "{}", readOnlyBuffer.get());
        log.info(readOnlyBuffer.toString());
         log.info(intBuffer.toString());

    }
}
