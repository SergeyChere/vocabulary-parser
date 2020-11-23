package com.example.demo.controller;

import com.example.demo.config.CacheExceptionStorage;
import com.example.demo.config.CacheJustWordsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
@RequestMapping("words")
public class JustWordsController {

    @Autowired
    private CacheJustWordsStorage cacheJustWordsStorage;

    @Autowired
    private CacheExceptionStorage cacheExceptionStorage;

    @GetMapping("/getAllManJustWords")
    public Enumeration<String> getAllManJustWords() {
        return cacheJustWordsStorage.manGenderStorageJustWords.keys();
    }

    @GetMapping("/getAllWomanJustWords")
    public Enumeration<String> getAllWomanJustWords() {
        return cacheJustWordsStorage.womanGenderStorageJustWords.keys();
    }

    @GetMapping("/getAllMiddleJustWords")
    public Enumeration<String> getAllMiddleJustWords() {
        return cacheJustWordsStorage.middleGenderStorageJustWords.keys();
    }

    @GetMapping("/getAllUnknownJustWords")
    public Enumeration<String> getAllUnknownJustWords() {
        return cacheJustWordsStorage.unknownGenderStorageJustWords.keys();
    }

    @GetMapping("/getAllAdverbStorageJustWords")
    public Enumeration<String> getAllAdverbStorageJustWords() {
        return cacheJustWordsStorage.adverbStorageJustWords.keys();
    }

    @GetMapping("/getAllPluralStorageJustWords")
    public Enumeration<String> getAllPluralStorageJustWords() {
        return cacheJustWordsStorage.pluralStorageJustWords.keys();
    }

    @GetMapping("/getAllExceptionsStorageJustWords")
    public Enumeration<String> getAllExceptionsStorageJustWords() {
        return cacheExceptionStorage.exceptionsStorage.keys();
    }
}
