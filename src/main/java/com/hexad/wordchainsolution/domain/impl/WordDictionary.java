package com.hexad.wordchainsolution.domain.impl;

import com.hexad.wordchainsolution.domain.IWordDictionary;
import com.hexad.wordchainsolution.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Component
public class WordDictionary implements IWordDictionary {

    private HashMap<Integer, Set<String>> wordLengthWiseWordBags = new LinkedHashMap<>();
    private String inputWordsFilePath;

    @Autowired
    public WordDictionary(@Value("${input.words.file.path:}") String inputWordsFilePath) {
        this.inputWordsFilePath = inputWordsFilePath;
        initialize();
    }

    public void initialize() {

        List<String> words = readWordsFromFile(inputWordsFilePath);

        if (words.size() < 2) {
            throw new IllegalArgumentException("Word list must contain at least 2 words");
        }
        seggregateWordLengthWiseDictionary(words);
    }

    private static List<String> readWordsFromFile(String inputWordsFilePath) {
        List<String> dictionaryWords = null;
        try {
            dictionaryWords = Utils.getWordsFromFile(inputWordsFilePath);
        } catch (FileNotFoundException fex) {
            System.out.println("Dictionary word list not found!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dictionaryWords;
    }

    private void seggregateWordLengthWiseDictionary(List<String> words) {

        words.stream().forEach((word) -> {
            int length = word.length();
            Set<String> wordBag;

            if (!wordLengthWiseWordBags.containsKey(length)) {
                wordBag = new HashSet<>();
            } else {
                wordBag = wordLengthWiseWordBags.get(length);
            }
            wordBag.add(word);
            wordLengthWiseWordBags.put(length, wordBag);

        });

    }

    @Override
    public Set<String> getWordsWithLength(int length) {

        return wordLengthWiseWordBags.get(length);
    }
}
