package com.liurui.defines.structures;

import com.liurui.answers.structures.HashTableUsingLinkImpl;
import org.junit.Test;

public class HashTableUsingLinkTest {
    @Test
    public void test(){
        HashTableUsingLink  item = new HashTableUsingLinkImpl();

        new HashtableTest().test(item);
    }
}