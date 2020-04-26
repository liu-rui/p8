/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.liurui.samples;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * @author Aleksey Shipilev
 */
public class JOLSample_01_Basic {

    /*
     * This sample showcases the basic field layout.
     * You can see a few notable things here:
     *   a) how much the object header consumes;
     *   b) how fields are laid out;
     *   c) how the external alignment beefs up the object size
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(A.class).toPrintable());

        //# Running 64-bit HotSpot VM.
        //# Using compressed oop with 3-bit shift.
        //# Using compressed klass with 3-bit shift.
        //# WARNING | Compressed references base/shifts are guessed by the experiment!
        //# WARNING | Therefore, computed addresses are just guesses, and ARE NOT RELIABLE.
        //# WARNING | Make sure to attach Serviceability Agent to get the reliable addresses.
        //# Objects are 8 bytes aligned.
        //# Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
        //# Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
        //
        //com.liurui.samples.JOLSample_01_Basic$A object internals:
        // OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
        //      0    12           (object header)                           N/A
        //     12     1   boolean A.f                                       N/A
        //     13     3           (loss due to the next object alignment)
        //Instance size: 16 bytes
        //Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
        //
        //
        //Process finished with exit code 0

        //Field sizes by type
        //                size:oopSize,
        //                sizes.booleanSize,
        //                sizes.byteSize,
        //                sizes.charSize,
        //                sizes.shortSize,
        //                sizes.intSize,
        //                sizes.floatSize,
        //                sizes.longSize,
        //                sizes.doubleSize
        //Array element sizes
        //                U.arrayIndexScale(Object[].class),
        //                U.arrayIndexScale(boolean[].class),
        //                U.arrayIndexScale(byte[].class),
        //                U.arrayIndexScale(char[].class),
        //                U.arrayIndexScale(short[].class),
        //                U.arrayIndexScale(int[].class),
        //                U.arrayIndexScale(float[].class),
        //                U.arrayIndexScale(long[].class),
        //                U.arrayIndexScale(double[].class)

        //分析：
        //1. 引用类型启动了-XX:+UseCompressedOops时为4byte,关闭后为8byte
        //2. 对齐在数据的后面

    }

    public static class A {
        boolean f;
    }

}
