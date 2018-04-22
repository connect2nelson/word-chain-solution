package com.hexad.wordchainsolution.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Utils {

    /**
     * Reads a file containing a word on each line
     * @param classPathFilename string to path of file on the classpath
     * @return List of words extracted from the file
     */
    public static List<String> getWordsFromFile(String classPathFilename) throws IOException {
        InputStream stream = Utils.class.getResourceAsStream(classPathFilename);

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(stream));

        List<String> words = new ArrayList<>();
        while (fileReader.ready()) {
            words.add(fileReader.readLine());
        }
        return words;
    }
}
