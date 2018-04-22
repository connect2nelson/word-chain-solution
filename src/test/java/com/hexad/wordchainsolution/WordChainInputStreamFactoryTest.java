package com.hexad.wordchainsolution;

import com.hexad.wordchainsolution.streams.factory.InputStreamAbstractFactory;
import org.junit.Before;
import org.junit.Test;

import com.hexad.wordchainsolution.streams.factory.InputStreamFactory;
import com.hexad.wordchainsolution.streams.WordPairConsoleWordChainInputStream;

import static org.mockito.Mockito.when;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class WordChainInputStreamFactoryTest {

    private InputStream mockDefaultStream;

    private InputStreamAbstractFactory abstractFactory;
    private WordPairConsoleWordChainInputStream consoleInputStream;

    @Before
    public void setup() throws Exception {
    	abstractFactory = mock(InputStreamAbstractFactory.class);
    	mockDefaultStream = mock(InputStream.class); 
    	consoleInputStream = mock(WordPairConsoleWordChainInputStream.class);
    	when(InputStreamFactory.getInputStream(abstractFactory)).thenReturn(consoleInputStream);
    	when(consoleInputStream.getInputStream()).thenReturn(mockDefaultStream);
    }

    @Test
    public void testGetDefaultStream() throws Exception {
        assertEquals(mockDefaultStream, InputStreamFactory.getInputStream(abstractFactory).getInputStream());
    }
}
