package com.liurui.defines.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HashtableTest {

    public void test(Hashable hashtable) {
        assertEquals(0, hashtable.getSize());

        hashtable.put(10, "a");
        assertEquals(1, hashtable.getSize());

        hashtable.remove(10);
        assertEquals(0, hashtable.getSize());
        assertNull(hashtable.get(10));

        hashtable.put(10, "a");
        hashtable.put(11, "b");
        hashtable.put(20, "c");
        hashtable.put(30, "d");
        hashtable.put(10, "e");
        assertEquals(4, hashtable.getSize());
        assertEquals("e", hashtable.get(10));
        assertEquals("b", hashtable.get(11));
        assertEquals("c", hashtable.get(20));
        assertEquals("d", hashtable.get(30));

        hashtable.put(40, "e");
        hashtable.put(41, "f");
        hashtable.put(42, "g");
        hashtable.put(43, "h");
        hashtable.put(44, "i");
        hashtable.put(45, "j");
        hashtable.put(46, "k");


        assertEquals(11, hashtable.getSize());
        assertEquals("e", hashtable.get(10));
        assertEquals("b", hashtable.get(11));
        assertEquals("c", hashtable.get(20));
        assertEquals("d", hashtable.get(30));
        assertEquals("e", hashtable.get(40));
        assertEquals("f", hashtable.get(41));
        assertEquals("g", hashtable.get(42));
        assertEquals("h", hashtable.get(43));
        assertEquals("i", hashtable.get(44));
        assertEquals("j", hashtable.get(45));
        assertEquals("k", hashtable.get(46));
    }
}