package com.example.demo.config;

import com.example.demo.model.Word;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@Configuration
public class CacheExceptionStorage {

    public Hashtable<String, String> exceptionsStorage = new Hashtable<>();
    public Map<Integer, Word> exceptionsStorageWithText = new HashMap<>();

    @Bean
    @Qualifier("exceptionsStorageWithText")
    public Map<Integer, Word> getExceptionsStorageWithText() {
        return exceptionsStorageWithText;
    }

    @Bean
    @Qualifier("exceptionsStorage")
    public Hashtable<String, String> getExceptionStorage() {
        return exceptionsStorage;
    }

}
