package com.liurui.defines.structures.string;

import com.liurui.answers.structures.string.PalindromeImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PalindromeTest {
    @Test
    public void test() {
        Palindrome item = new PalindromeImpl();

        assertTrue(item.isPalindrome("abcba"));
        assertTrue(item.isPalindrome("abccba"));
        assertFalse(item.isPalindrome("abdbd"));
    }
}
