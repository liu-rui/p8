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
public class JOLSample_03_Packing {

    /**
     * This is the example how VM packs the fields.
     *
     * JVMs pack the fields to minimize the memory footprint. Run
     * this example and see the fields are densely packed, and gaps
     * are minimal. It is achieved by aligning fields in 8->4->2->1
     * order, because it can not break the initial alignment, once we
     * align the 8-byte field. The gap resulted in the initial 8-byte
     * align can be taken by one or few smaller-sized fields.
     *
     * Note that the actual field order is very different from the
     * declared order. Nothing in the JVM spec requires otherwise.
     **/

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(A.class).toPrintable());

        //# WARNING | Make sure to attach Serviceability Agent to get the reliable addresses.
        //# Objects are 8 bytes aligned.
        //# Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
        //# Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
        //
        //com.liurui.samples.JOLSample_03_Packing$A object internals:
        // OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
        //      0    12           (object header)                           N/A
        //     12     4     float A.f1                                      N/A
        //     16     8    double A.d1                                      N/A
        //     24     8    double A.d2                                      N/A
        //     32     8      long A.l1                                      N/A
        //     40     8      long A.l2                                      N/A
        //     48     4     float A.f2                                      N/A
        //     52     4       int A.i1                                      N/A
        //     56     4       int A.i2                                      N/A
        //     60     2      char A.c1                                      N/A
        //     62     2      char A.c2                                      N/A
        //     64     2     short A.s1                                      N/A
        //     66     2     short A.s2                                      N/A
        //     68     1   boolean A.bo1                                     N/A
        //     69     1   boolean A.bo2                                     N/A
        //     70     1      byte A.b1                                      N/A
        //     71     1      byte A.b2                                      N/A
        //Instance size: 72 bytes
        //Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        //
        //
        //Process finished with exit code 0
    }

    public static class A {
        boolean bo1, bo2;
        byte b1, b2;
        char c1, c2;
        double d1, d2;
        float f1, f2;
        int i1, i2;
        long l1, l2;
        short s1, s2;
    }

}
