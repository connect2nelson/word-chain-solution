package com.hexad.wordchainsolution.domain;


import com.hexad.wordchainsolution.exception.InvalidPairException;
import com.hexad.wordchainsolution.model.WordChain;
import com.hexad.wordchainsolution.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Component
public class WordChainSolver implements CommandLineRunner {

    private static final String DICTIONARY_FILENAME = "/words.txt";

    private IChainFinder finder;
    private IPairExtractor extractor;

    private String[] pair;
    private boolean isFinished;

    @Autowired
    public WordChainSolver(IChainFinder chainFinder, IPairExtractor pairExtractor) {
        this.finder = chainFinder;
        this.extractor = pairExtractor;
        this.isFinished = false;
    }

    public String getNextResult() throws IOException, InvalidPairException {
        if (pair == null) {
            pair = extractor.nextPair();
            if (pair.length != 2) {
                isFinished = true;
                return null;
            }
        }

        WordChain chain = finder.getShortestChain(pair[0], pair[1]);
        pair = null;

        if (chain != null) {
            String chainText = chain.toString();
            return String.format("YES - %s", chainText);
        } else {
            return "NO";
        }
    }

    public boolean isFinished() throws InvalidPairException {
        if (!isFinished) {
            if (pair != null) {
                return false;
            } else {
                try {
                    pair = extractor.nextPair();
                    if (pair.length != 2) {
                        isFinished = true;
                    }
                } catch (IOException ex) {
                    isFinished = true;
                }
            }
        }
        return isFinished;
    }

    private static List<String> readWordsFromFile() {
        List<String> dictionaryWords = null;
        try {
            dictionaryWords = Utils.getWordsFromFile(DICTIONARY_FILENAME);
        } catch (FileNotFoundException fex) {
            System.out.println("Dictionary word list not found!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dictionaryWords;
    }

    @Override
    public void run(String... args) throws Exception {

        // Repeat until the end of the input stream
        while (!isFinished()) {
            try {
                String result = getNextResult();
                System.out.println(result);
            } catch (IOException ex) {
                return;
            }
        }

    }
}
