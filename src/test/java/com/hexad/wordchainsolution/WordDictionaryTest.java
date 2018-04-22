package com.hexad.wordchainsolution;

import org.junit.Test;

import com.hexad.wordchainsolution.domain.impl.WordDictionary;

import static org.junit.Assert.assertArrayEquals;

public class WordDictionaryTest {

    @Test
    public void testDifferentLengthDictionary() {
        WordDictionary dictionary = new WordDictionary("/twowords.txt");

        assertArrayEquals(new String[]{"a"}, dictionary.getWordsWithLength(1).toArray());
        assertArrayEquals(new String[]{"ab"}, dictionary.getWordsWithLength(2).toArray());
    }

    @Test
    public void testDuplicateElementsDeletedFromDictionary() {
        WordDictionary dictionary = new WordDictionary("/oneWordWithDuplicates.txt");
        assertArrayEquals(new String[]{"a"}, dictionary.getWordsWithLength(1).toArray());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIntializeConstructorWithEmptyWordList() {
        WordDictionary wordDictionary = new WordDictionary("/oneWord.txt");
        wordDictionary.initialize();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testOnlyOneWordInDictionary() {
        WordDictionary wordDictionary = new WordDictionary("/noWords.txt");
        wordDictionary.initialize();
    }

}
