package com.liurui.defines.structures.string;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class PermutationTest {
    public void test(Permutation item) {
        test(item, new String[]{"xy", "yx"}, "xy");
        test(item, new String[]{"xyz", "xzy", "yxz", "yzx", "zxy", "zyx"}, "zxy");
        test(item, new String[]{"axyz", "axzy", "ayxz", "ayzx", "azxy", "azyx", "xayz", "xazy", "xyaz", "xyza", "xzay", "xzya", "yaxz", "yazx", "yxaz", "yxza", "yzax", "yzxa", "zaxy", "zayx", "zxay", "zxya", "zyax", "zyxa"}, "azxy");
    }

    private void test(Permutation item, String[] expecteds, String str) {
        String[] strings = item.permutation(str);

        Arrays.sort(strings);
        assertArrayEquals(expecteds, strings);
    }
}
