package com.hexad.wordchainsolution.streams;

import java.io.InputStream;

public class WordPairConsoleWordChainInputStream extends WordChainInputStream {

	@Override
	public InputStream getInputStream() throws Exception {
		return System.in;
	}

}
