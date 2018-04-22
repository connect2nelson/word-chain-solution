package com.hexad.wordchainsolution.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordChain {
    private List<String> words;

    public List<String> getWords() {
        return words;
    }

    public WordChain(List<String> words) {
        this.words = words;
    }

    public WordChain(WordChain chain) {
        this.words = new ArrayList<>(chain.words);
    }

    public String getLastWord() {
        return words.get(words.size() - 1);
    }

    @Override
    public String toString() {
        return words.stream()
                .collect(Collectors.joining(" "));
    }
}
