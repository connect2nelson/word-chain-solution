package com.hexad.wordchainsolution.streams.factory;


import com.hexad.wordchainsolution.streams.WordChainInputStream;
import com.hexad.wordchainsolution.streams.WordPairConsoleWordChainInputStream;

public class ConsoleISFactory implements InputStreamAbstractFactory {

	@Override
	public WordChainInputStream createInputStream() {
		// TODO Auto-generated method stub
		return new WordPairConsoleWordChainInputStream();
	}

}
