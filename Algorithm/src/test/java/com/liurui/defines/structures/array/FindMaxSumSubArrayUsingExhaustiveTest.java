package com.liurui.defines.structures.array;

import com.liurui.answers.structures.array.FindMaxSumSubArrayUsingExhaustiveImpl;
import org.junit.Test;

public class FindMaxSumSubArrayUsingExhaustiveTest {
    @Test
    public void test() {
        FindMaxSumSubArray item = new FindMaxSumSubArrayUsingExhaustiveImpl();

        new FindMaxSumSubArrayTest().test(item);
    }
}
