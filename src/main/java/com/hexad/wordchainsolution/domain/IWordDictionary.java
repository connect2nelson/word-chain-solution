package com.hexad.wordchainsolution.domain;

import java.util.Set;

public interface IWordDictionary {
    Set<String> getWordsWithLength(int length);
}
