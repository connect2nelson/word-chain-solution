package com.hexad.wordchainsolution.streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class WordPairFileWordChainInputStream extends WordChainInputStream {

	private String fileName;

	public WordPairFileWordChainInputStream(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public InputStream getInputStream() throws FileNotFoundException {
		File f = new File(fileName);
		return new FileInputStream(f);

	}

}
