package com.hexad.wordchainsolution.domain.impl;

import com.hexad.wordchainsolution.domain.IPairExtractor;
import com.hexad.wordchainsolution.exception.InvalidPairException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultPairExtractorTest {

    public @Rule
    ExpectedException thrown = ExpectedException.none();

    private BufferedReader mockReader;

    @Before
    public void setup() {
        mockReader = mock(BufferedReader.class);
    }


    @Test
    public void testNextPairValid() throws IOException,InvalidPairException {
        when(mockReader.readLine()).thenReturn("abc xyz");
        IPairExtractor extractor = new DefaultPairExtractor(mockReader);
        String[] result = extractor.nextPair();

        assertNotNull(result);
        assertArrayEquals(new String[] {"abc", "xyz"}, result);
    }

    @Test(expected = InvalidPairException.class)
    public void testNextPairEmptyString() throws IOException, InvalidPairException {
        when(mockReader.readLine()).thenReturn("");
        IPairExtractor extractor = new DefaultPairExtractor(mockReader);
        extractor.nextPair();
    }


    @Test(expected = InvalidPairException.class)
    public void testNextPairTooManyTokens() throws IOException, InvalidPairException {
        when(mockReader.readLine()).thenReturn("abc xyz 1234");
        IPairExtractor extractor = new DefaultPairExtractor(mockReader);
        extractor.nextPair();
    }
}