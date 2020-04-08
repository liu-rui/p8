package com.liurui.defines.structures;

import static org.junit.Assert.*;


/**
 * https://www.jianshu.com/p/a64d1ef95980
 */
public class ArrayListTest {

    public void test(ArrayListable ary) {
        assertEquals(0, ary.getSize());

        ary.add(10);
        assertEquals(1, ary.getSize());
        assertEquals(10, ary.get(0));
        assertTrue(ary.contains(10));
        assertFalse(ary.contains(20));

        ary.remove();
        assertEquals(0, ary.getSize());

        ary.add(10);
        ary.add(20);
        ary.insert(0, 30);
        assertEquals(3, ary.getSize());
        assertEquals(30, ary.get(0));
        assertEquals(10, ary.get(1));
        assertEquals(20, ary.get(2));
        assertTrue(ary.contains(10));
        assertTrue(ary.contains(20));
        assertTrue(ary.contains(30));
        assertFalse(ary.contains(40));

        ary.remove(1);
        assertEquals(2, ary.getSize());
        assertEquals(30, ary.get(0));
        assertEquals(20, ary.get(1));
        assertFalse(ary.contains(10));

        ary.remove();
        assertEquals(1, ary.getSize());
        assertEquals(30, ary.get(0));
        assertFalse(ary.contains(20));

        ary.clear();
        assertEquals(0, ary.getSize());
    }
}