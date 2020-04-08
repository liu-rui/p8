package com.liurui.defines.structures.array;

import com.liurui.answers.structures.array.FindMaxSumSubArrayUsingDynamicPlanningImpl;
import org.junit.Test;

public class FindMaxSumSubArrayUsingDynamicPlanningTest {
    @Test
    public void test() {
        FindMaxSumSubArray item = new FindMaxSumSubArrayUsingDynamicPlanningImpl();

        new FindMaxSumSubArrayTest().test(item);
    }
}
