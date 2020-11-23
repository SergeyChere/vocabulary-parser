package com.example.demo.config;

import com.example.demo.model.Word;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CacheGenderStorage {

    public Map<Integer, Word> manGenderStorage = new HashMap<>();
    public Map<Integer, Word> womanGenderStorage = new HashMap<>();
    public Map<Integer, Word> middleGenderStorage = new HashMap<>();
    public Map<Integer, Word> unknown = new HashMap<>();

    @Bean
    @Qualifier("manGenderStorage")
    public Map<Integer, Word> getManGenderStorage() {
        return manGenderStorage;
    }

    @Bean
    @Qualifier("womanGenderStorage")
    public Map<Integer, Word> getWomanGenderStorage() {
        return womanGenderStorage;
    }

    @Bean
    @Qualifier("middleGenderStorage")
    public Map<Integer, Word> getMiddleGenderStorage() {
        return middleGenderStorage;
    }

    @Bean
    @Qualifier("unknown")
    public Map<Integer, Word> getUnknown() {
        return unknown;
    }
}
