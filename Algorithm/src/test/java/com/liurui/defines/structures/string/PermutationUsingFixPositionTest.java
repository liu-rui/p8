package com.liurui.defines.structures.string;

import com.liurui.answers.structures.string.PermutatioUsingFixPositionImpl;
import org.junit.Test;

public class PermutationUsingFixPositionTest {

    @Test
    public void test() {
        Permutation item = new PermutatioUsingFixPositionImpl();

        new PermutationTest().test(item);
    }
}
