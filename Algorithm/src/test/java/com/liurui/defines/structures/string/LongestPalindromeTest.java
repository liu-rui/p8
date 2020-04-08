package com.liurui.defines.structures.string;

import com.liurui.answers.structures.string.LongestPalindromeImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LongestPalindromeTest {
    @Test
    public void test() {
        LongestPalindrome item = new LongestPalindromeImpl();

        assertEquals("cdedc", item.search("abcdedco"));
        assertEquals("cdefgfedc", item.search("abcdefgfedcgcda"));
    }
}
