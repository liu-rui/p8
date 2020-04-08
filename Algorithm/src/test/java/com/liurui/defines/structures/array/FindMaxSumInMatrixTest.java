package com.liurui.defines.structures.array;

import com.liurui.answers.structures.array.FindMaxSumInMatrixImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindMaxSumInMatrixTest {
    @Test
    public void test() {
        FindMaxSumInMatrix item = new FindMaxSumInMatrixImpl();

        assertEquals(14,
                item.calc(new int[][]{
                        {-1, 2, -1, 3, -4},
                        {2, 3, 4, -5, 1},
                        {1, -1, 5, -3, -2}}));
        assertEquals(15,
                item.calc(new int[][]{
                        {0, -2, -7, 0},
                        {9, 2, -6, 2},
                        {-4, 1, -4, 1},
                        {-1, 8, 0, -2}}));
    }
}
