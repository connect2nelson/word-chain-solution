package com.hexad.wordchainsolution.domain;

import java.io.IOException;

import com.hexad.wordchainsolution.exception.InvalidPairException;

public interface IPairExtractor {

	String[] nextPair() throws IOException, InvalidPairException;
}
