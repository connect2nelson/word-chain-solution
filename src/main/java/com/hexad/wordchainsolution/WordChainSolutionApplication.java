package com.hexad.wordchainsolution;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.hexad.wordchainsolution")
public class WordChainSolutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordChainSolutionApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> System.out.println("Starting WordChainSolution generator applciation with args = " + args);
    }

}
