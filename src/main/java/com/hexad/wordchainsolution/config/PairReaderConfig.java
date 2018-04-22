package com.hexad.wordchainsolution.config;

import com.hexad.wordchainsolution.streams.factory.ConsoleISFactory;
import com.hexad.wordchainsolution.streams.factory.FileISFactory;
import com.hexad.wordchainsolution.streams.factory.InputStreamFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Configuration
public class PairReaderConfig {

    @Value("${input.pair.filepath:}")
    private String fileName;

    @Bean
    public BufferedReader getReader() throws Exception {
        InputStream inputStream = null;

        if (StringUtils.isEmpty(fileName) ){
            inputStream = InputStreamFactory.getInputStream(new ConsoleISFactory()).getInputStream();

        }else {
            inputStream = InputStreamFactory.getInputStream(new FileISFactory(fileName)).getInputStream();
        }

        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
