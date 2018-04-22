package com.hexad.wordchainsolution.streams.factory;

import com.hexad.wordchainsolution.streams.WordChainInputStream;

public class InputStreamFactory {
	
	public static WordChainInputStream getInputStream(InputStreamAbstractFactory factory){
		return factory.createInputStream();
	}

}
