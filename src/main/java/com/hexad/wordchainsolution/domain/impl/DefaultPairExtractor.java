package com.hexad.wordchainsolution.domain.impl;

import com.hexad.wordchainsolution.domain.IPairExtractor;
import com.hexad.wordchainsolution.exception.InvalidPairException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class DefaultPairExtractor implements IPairExtractor {

    private BufferedReader reader;

    @Autowired
    public DefaultPairExtractor(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String[] nextPair() throws IOException, InvalidPairException {

        String[] nextPair = {};

        System.out.println("Please enter the start word followed by the end word. Forex : read deed ");
        String line = reader.readLine();

        if (StringUtils.isEmpty(line)){
            return nextPair;
        }

        if (line != null) {
            nextPair = line.split(" ");

            if (nextPair.length == 2) {
                return nextPair;
            } else {
                throw new InvalidPairException();
            }
        }

        return nextPair;
    }
}