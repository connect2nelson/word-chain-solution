package com.hexad.wordchainsolution.domain.impl;

import com.hexad.wordchainsolution.domain.IChainFinder;
import com.hexad.wordchainsolution.domain.IWordDictionary;
import com.hexad.wordchainsolution.model.WordChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ChainFinder implements IChainFinder {

    private IWordDictionary dictionary;
    private Comparator<String> wordComparator;

    private boolean isShortestChainCaseSensitive;

    @Autowired
    public ChainFinder(IWordDictionary wordDictionary,
                       Comparator<String> wordComparator) {
        this.dictionary = wordDictionary;

        this.wordComparator = wordComparator;
    }


    @Override
    public WordChain getShortestChain(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return null;
        }

        Set<String> words = dictionary.getWordsWithLength(word1.length());

        List<WordChain> paths = new ArrayList<>();
        List<String> initialChainWords = Arrays.asList(word1);
        paths.add(new WordChain(initialChainWords));

        // Add the starting word to words traversed, so we don't end up looping back to the start
        Set<String> wordsTraversed = new HashSet<>();
        wordsTraversed.add(word1);
        return getShortestChain(word2, paths, words, wordsTraversed);
    }

    /**
     * Recursive function for finding the shortest chain
     * Uses a modified breadth first search algorithm
     *
     * @param paths          The current paths found so far
     * @param dWords         The dictionary of valid words to be used in the chain
     * @param goal           The word to find as the end result
     * @param wordsTraversed A list of words already included in a chain
     * @return The shortest word chain, or an empty optional if no chain is found
     */
    private WordChain getShortestChain(String goal, List<WordChain> paths, Set<String> dWords,
                                       Set<String> wordsTraversed) {
        List<WordChain> newPaths = new ArrayList<>();

        for (String dWord : dWords) {
            for (WordChain path : paths) {
                String pWord = path.getLastWord();
                if (wordComparator.compare(pWord, dWord) == 0) {
                    WordChain newPath = new WordChain(path);
                    List<String> newPathWords = newPath.getWords();

                    // If we've already come across this word, it means we've found a shorter or equal length path
                    // to it, so we can end this path here as it can never be the shortest
                    if (!wordsTraversed.contains(dWord)) {
                        newPathWords.add(dWord);
                        wordsTraversed.add(dWord);

                        // The shortest chain has been found (because we use breadth first search)
                        // so we can stop searching now

                        if ((!isShortestChainCaseSensitive && dWord.equalsIgnoreCase(goal))
                                || (isShortestChainCaseSensitive && dWord.equals(goal))) {
                            return newPath;
                        }

                        newPaths.add(newPath);
                    }
                }
            }
        }
        if (newPaths.size() == 0) {
            return null;
        }

        return getShortestChain(goal, newPaths, dWords, wordsTraversed);
    }

    public void setShortestChainCaseSensitive(boolean shortestChainCaseSensitive) {
        isShortestChainCaseSensitive = shortestChainCaseSensitive;
    }

}
