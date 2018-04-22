package com.hexad.wordchainsolution.comparator;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SingleCharEditComparatorTest {

    private static SingleCharEditComparator comparator;

    @BeforeClass
    public static void setup() {
        comparator = new SingleCharEditComparator();
    }

    @Test
    public void testCompareOneEdit() {
        assertEquals(0, comparator.compare("abc", "abz"));
    }

    @Test
    public void testGetWordChainsCaseInsensitiveMatches() {
        assertEquals(0, comparator.compare("abC", "ABD"));
    }

    @Test
    public void testCompareTwoEdits() {
        assertNotEquals(0, comparator.compare("abc", "ayz"));
    }

    @Test
    public void testCompareDifferentWordLengths() {
        assertNotEquals(0, comparator.compare("abc", "abcd"));
    }
}