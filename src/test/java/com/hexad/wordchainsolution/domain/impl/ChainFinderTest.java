package com.hexad.wordchainsolution.domain.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;

import com.hexad.wordchainsolution.domain.IWordDictionary;
import com.hexad.wordchainsolution.model.WordChain;
import org.junit.Before;
import org.junit.Test;

import com.hexad.wordchainsolution.comparator.SingleCharEditComparator;

public class ChainFinderTest {

    private ChainFinder caseInsensitiveFinder;
    private ChainFinder caseSensitiveFinder;
    private IWordDictionary mockDictionary;

    @Before
    public void setup() {
        mockDictionary = mock(IWordDictionary.class);
        when(mockDictionary.getWordsWithLength(3)).thenReturn(new HashSet<>(Arrays.asList("abc", "abd", "axd")));
        when(mockDictionary.getWordsWithLength(4)).thenReturn(new HashSet<>(Arrays.asList("abcd")));

        caseInsensitiveFinder = new ChainFinder(mockDictionary, new SingleCharEditComparator());
        caseInsensitiveFinder.setShortestChainCaseSensitive(false);

        caseSensitiveFinder = new ChainFinder(mockDictionary, new SingleCharEditComparator());
        caseSensitiveFinder.setShortestChainCaseSensitive(true);
    }

    @Test
    public void testGetWordChainsWhichHas2ChainStrings() {
        WordChain chain = caseInsensitiveFinder.getShortestChain("abc", "abd");

        assertNotNull(chain);
        assertArrayEquals(new String[] {"abc", "abd"}, chain.getWords().toArray());
    }

    @Test
    public void testGetWordChainsWhichHasMultipleChainStrings() {
        WordChain chain = caseInsensitiveFinder.getShortestChain("abc", "axd");

        assertNotNull(chain);
        assertArrayEquals(new String[] {"abc", "abd", "axd"}, chain.getWords().toArray());
    }

    @Test
    public void testGetWordChainsShortestPath() {
        when(mockDictionary.getWordsWithLength(3)).thenReturn(new HashSet<>(Arrays.asList("abc", "abd", "abe", "abf")));
        WordChain chain = caseInsensitiveFinder.getShortestChain("abc", "abf");

        assertNotNull(chain);
        assertArrayEquals(new String[] {"abc", "abf"}, chain.getWords().toArray());
    }

    @Test
    public void testGetWordChainsCaseInsensitive() {
        when(mockDictionary.getWordsWithLength(3)).thenReturn(new HashSet<>(Arrays.asList("abc", "abd", "abe", "abF")));
        WordChain chain = caseInsensitiveFinder.getShortestChain("abc", "abf");

        assertNotNull(chain);
        assertArrayEquals(new String[] {"abc", "abF"}, chain.getWords().toArray());
    }
    
    @Test
    public void testGetWordChainsCaseSensitiveFailure() {
        when(mockDictionary.getWordsWithLength(3)).thenReturn(new HashSet<>(Arrays.asList("abc", "abd", "abe", "abF")));
        WordChain chain = caseSensitiveFinder.getShortestChain("abc", "abf");
        assertNull(chain);
    }

    @Test
    public void testGetWordChainsInvalidWordLink() {
        assertNull(caseInsensitiveFinder.getShortestChain("abc", "qqq"));
    }


    @Test
    public void testGetWordChainsDifferentWordLengths() {
        assertNull(caseInsensitiveFinder.getShortestChain("abc", "abcd"));
    }

}
