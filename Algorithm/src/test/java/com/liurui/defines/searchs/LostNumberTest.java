package com.liurui.defines.searchs;

import com.liurui.answers.searchs.LostNumberImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class LostNumberTest {
    @Test
    public void test() {
        ArrayList<Integer> container = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            container.add(i);
        }
        Collections.shuffle(container);

        int ret = container.remove(30);
        int[] ary = new int[container.size()];

        for (int i = 0; i < ary.length; i++) {
            ary[i] = container.get(i);
        }
        LostNumber item = new LostNumberImpl();

        assertEquals(ret, item.find(ary));
    }
}
