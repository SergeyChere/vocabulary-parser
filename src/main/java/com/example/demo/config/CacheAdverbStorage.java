package com.example.demo.config;

import com.example.demo.model.Word;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CacheAdverbStorage {

    public Map<Integer, Word> adverbs = new HashMap<>();

    @Bean
    public Map<Integer, Word> getAdverbsStorage() {
        return adverbs;
    }
}
