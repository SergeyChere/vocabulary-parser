package com.example.demo.config;

import com.example.demo.model.Word;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CachePluralStorage {

    public Map<Integer, Word> plurals = new HashMap<>();

    @Bean
    public Map<Integer, Word> getPluralsStorage() {
        return plurals;
    }
}
