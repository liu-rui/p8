package com.liurui.defines.structures.string;

import com.liurui.answers.structures.string.ContainsUsingHashImpl;
import org.junit.Test;

public class ContainsUsingHashTest {
    @Test
    public void test(){
        Contains  item = new ContainsUsingHashImpl();

        new ContainsTest().test(item);
    }
}
