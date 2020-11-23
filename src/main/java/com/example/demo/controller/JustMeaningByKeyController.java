package com.example.demo.controller;

import com.example.demo.config.CacheExceptionStorage;
import com.example.demo.config.CacheJustWordsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("keys")
public class JustMeaningByKeyController {

    @Autowired
    private CacheJustWordsStorage cacheJustWordsStorage;

    @Autowired
    private CacheExceptionStorage cacheExceptionStorage;


    @GetMapping("/getManJustWordsByKey/{key}")
    public String getManJustWordsByKey(@PathVariable("key") String key) {
        return cacheJustWordsStorage.manGenderStorageJustWords.get(key.toUpperCase());
    }

    @GetMapping("/getWomanJustWordsByKey/{key}")
    public String getWomanJustWordsByKey(@PathVariable("key") String key) {
        return cacheJustWordsStorage.womanGenderStorageJustWords.get(key.toUpperCase());
    }

    @GetMapping("/getMiddleJustWordsByKey/{key}")
    public String getMiddleJustWordsByKey(@PathVariable("key") String key) {
        return cacheJustWordsStorage.middleGenderStorageJustWords.get(key.toUpperCase());
    }

    @GetMapping("/getUnknownJustWordsByKey/{key}")
    public String getUnknownJustWordsByKey(@PathVariable("key") String key) {
        return cacheJustWordsStorage.unknownGenderStorageJustWords.get(key.toUpperCase());
    }

    @GetMapping("/getAdverbStorageJustWordsByKey/{key}")
    public String getAdverbStorageJustWordsByKey(@PathVariable("key") String key) {
        return cacheJustWordsStorage.adverbStorageJustWords.get(key.toUpperCase());
    }

    @GetMapping("/getPluralStorageJustWordsByKey/{key}")
    public String getPluralStorageJustWordsByKey(@PathVariable("key") String key) {
        return cacheJustWordsStorage.pluralStorageJustWords.get(key.toUpperCase());
    }

    @GetMapping("/getExceptionsStorageJustWordsByKey/{key}")
    public String getExceptionsStorageJustWordsByKey(@PathVariable("key") String key) {
        return cacheExceptionStorage.exceptionsStorage.get(key.toUpperCase());
    }
}
