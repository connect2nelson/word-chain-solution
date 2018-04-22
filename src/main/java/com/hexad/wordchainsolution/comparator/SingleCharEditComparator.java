package com.hexad.wordchainsolution.comparator;

import org.springframework.stereotype.Component;

import java.util.Comparator;


@Component
public class SingleCharEditComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        String s1Lower = s1.toLowerCase();
        String s2Lower = s2.toLowerCase();
        if (s1.length() == s2.length()) {
            int diff = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1Lower.charAt(i) != s2Lower.charAt(i)) {
                    diff++;
                }
            }
            return diff == 1 ? 0 : 1;
        }
        return 1;
    }
}
