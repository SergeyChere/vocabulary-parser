package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class CacheJustWordsStorage {

    public Hashtable<String, String> manGenderStorageJustWords = new Hashtable<>();
    public Hashtable<String, String> womanGenderStorageJustWords = new Hashtable<>();
    public Hashtable<String, String> middleGenderStorageJustWords = new Hashtable<>();
    public Hashtable<String, String> unknownGenderStorageJustWords = new Hashtable<>();
    public Hashtable<String, String> adverbStorageJustWords = new Hashtable<>();
    public Hashtable<String, String> pluralStorageJustWords = new Hashtable<>();

    @Bean
    @Qualifier("manGenderStorageJustWords")
    public Hashtable<String, String> getManGenderStorageJustWords() {
        return manGenderStorageJustWords;
    }

    @Bean
    @Qualifier("womanGenderStorageJustWords")
    public Hashtable<String, String> getWomanGenderStorageJustWords() {
        return womanGenderStorageJustWords;
    }

    @Bean
    @Qualifier("middleGenderStorageJustWords")
    public Hashtable<String, String> getMiddleGenderStorageJustWords() {
        return middleGenderStorageJustWords;
    }

    @Bean
    @Qualifier("unknownGenderStorageJustWords")
    public Hashtable<String, String> getUnknownGenderStorageJustWords() {
        return unknownGenderStorageJustWords;
    }

    @Bean
    @Qualifier("adverbStorageJustWords")
    public Hashtable<String, String> getAdverbStorageJustWords() {
        return adverbStorageJustWords;
    }

    @Bean
    @Qualifier("pluralStorageJustWords")
    public Hashtable<String, String> getPluralStorageJustWords() {
        return pluralStorageJustWords;
    }
}
