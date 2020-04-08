package com.liurui.defines.structures.tree;

import com.liurui.answers.structures.tree.HaFuManTreeImpl;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HaFuManTreeTest {

    @Test
    public void test(){
        HaFuManTree tree = new HaFuManTreeImpl();
        HashMap<String,Integer> keys = new HashMap<>();

        keys.put("a" , 145);
        keys.put("b" , 38);
        keys.put("d" , 45);
        keys.put("e" , 45);
        keys.put("f" , 33);
        keys.put("g" , 25);
        keys.put("z" , 1);
        keys.put("p" , 8);

        tree.generic(keys);


        assertEquals("0" ,   tree.getCode("a"));
        assertEquals("100" ,   tree.getCode("b"));
        assertEquals("101" ,   tree.getCode("d"));
        assertEquals("110" ,   tree.getCode("e"));
        assertEquals("1110" ,   tree.getCode("f"));
        assertEquals("11111" ,   tree.getCode("g"));
        assertEquals("111100" ,   tree.getCode("z"));
        assertEquals("111101" ,   tree.getCode("p"));
        assertEquals("0111110101" , tree.encode("agad"));
        assertEquals("agad" , tree.decode("0111110101"));
    }
}