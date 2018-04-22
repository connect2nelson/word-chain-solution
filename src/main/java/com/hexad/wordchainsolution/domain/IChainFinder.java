package com.hexad.wordchainsolution.domain;

import com.hexad.wordchainsolution.model.WordChain;

public interface IChainFinder {
    WordChain getShortestChain(String word1, String word2);
}
