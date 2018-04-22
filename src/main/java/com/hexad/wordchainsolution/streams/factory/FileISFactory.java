package com.hexad.wordchainsolution.streams.factory;

import com.hexad.wordchainsolution.streams.WordChainInputStream;
import com.hexad.wordchainsolution.streams.WordPairFileWordChainInputStream;

public class FileISFactory implements InputStreamAbstractFactory {

	private String fileName;
	
	public FileISFactory(String fileName) {
		
		this.fileName = fileName;
	}
	
	@Override
	public WordChainInputStream createInputStream() {
		return new WordPairFileWordChainInputStream(fileName);
	}

}
