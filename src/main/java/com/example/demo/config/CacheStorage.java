package com.example.demo.config;

import com.example.demo.model.Word;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CacheStorage {

    Map<Integer, Word> text = new HashMap<>();

    @Bean
    public Map<Integer, Word> getStorage() {
        return text;
    }
}
