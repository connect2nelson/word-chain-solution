package com.hexad.wordchainsolution;

import com.hexad.wordchainsolution.domain.IChainFinder;
import com.hexad.wordchainsolution.domain.IPairExtractor;
import com.hexad.wordchainsolution.domain.WordChainSolver;
import com.hexad.wordchainsolution.exception.InvalidPairException;
import com.hexad.wordchainsolution.model.WordChain;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WordChainSolverTest {

    private WordChainSolver solver;
    private IChainFinder mockChainFinder;
    private IPairExtractor mockPairExtractor;
    private WordChain wordChain;

    @Before
    public void setup() throws IOException, InvalidPairException {
        wordChain = new WordChain(Arrays.asList("abc", "abf", "adf"));
        mockChainFinder = mock(IChainFinder.class);
        mockPairExtractor = mock(IPairExtractor.class);
        when(mockPairExtractor.nextPair()).thenReturn(new String[]{"W1", "W2"});

        solver = new WordChainSolver(mockChainFinder, mockPairExtractor);
    }

    @Test
    public void testGetNextResultIsChain() throws IOException, InvalidPairException {
        when(mockChainFinder.getShortestChain(any(), any())).thenReturn(wordChain);
        assertEquals("YES - abc abf adf", solver.getNextResult());
    }

    @Test
    public void testGetNextResultNoChain() throws IOException, InvalidPairException {
        when(mockChainFinder.getShortestChain(any(), any())).thenReturn(null);
        assertEquals("NO", solver.getNextResult());
    }

}
