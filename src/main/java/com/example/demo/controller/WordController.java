package com.example.demo.controller;

import com.example.demo.config.*;
import com.example.demo.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("all")
public class WordController {

    @Autowired
    private CacheStorage cacheStorage;

    @Autowired
    private CacheGenderStorage cacheGenderStorage;

    @Autowired
    private CacheAdverbStorage cacheAdverbStorage;

    @Autowired
    private CachePluralStorage cachePluralStorage;

    @Autowired
    private CacheExceptionStorage cacheExceptionStorage;


    @GetMapping("/getSizes")
    public String getSizes() {
        return "All size is "+cacheStorage.getStorage().size()+"\n"+
                "Man gender size is "+cacheGenderStorage.manGenderStorage.size()+"\n"+
                "Woman gender size is "+cacheGenderStorage.womanGenderStorage.size()+"\n"+
                "Middle gender size is "+cacheGenderStorage.middleGenderStorage.size()+"\n"+
                "Unknown gender is "+cacheGenderStorage.unknown.size()+"\n"+
                "Adverbs vocabulary size is "+cacheAdverbStorage.adverbs.size()+"\n"+
                "Exception size is "+cacheExceptionStorage.exceptionsStorage.size()+" ;";
    }


    @GetMapping("/getOne/{number}")
    public Word getWord(@PathVariable("number") Integer number) {
        return cacheStorage.getStorage().get(number);
    }

    @GetMapping("/getAll")
    public Map<Integer, Word> getAllWord() {
        return cacheStorage.getStorage();
    }

    //----------------------------------

    @GetMapping("/getMan/{number}")
    public Word getMan(@PathVariable("number") Integer number) {
        return cacheGenderStorage.manGenderStorage.get(number);
    }

    @GetMapping("/getAllMen")
    public Map<Integer, Word> getAllMen() {
        return cacheGenderStorage.manGenderStorage;
    }

    @GetMapping("/getWoman/{number}")
    public Word getWoman(@PathVariable("number") Integer number) {
        return cacheGenderStorage.womanGenderStorage.get(number);
    }

    @GetMapping("/getWomen")
    public Map<Integer, Word> getWomen() {
        return cacheGenderStorage.womanGenderStorage;
    }

    @GetMapping("/getMiddle/{number}")
    public Word getMiddle(@PathVariable("number") Integer number) {
        return cacheGenderStorage.getMiddleGenderStorage().get(number);
    }

    @GetMapping("/getAllMiddle")
    public Map<Integer, Word> getAllMiddle() {
        return cacheGenderStorage.middleGenderStorage;
    }

    @GetMapping("/getUnknown/{number}")
    public Word getUnknown(@PathVariable("number") Integer number) {
        return cacheGenderStorage.getUnknown().get(number);
    }

    @GetMapping("/getAllUnknown")
    public Map<Integer, Word> getAllUnknown() {
        return cacheGenderStorage.unknown;
    }

    @GetMapping("/getAdverb/{number}")
    public Word getAdverb(@PathVariable("number") Integer number) {
        return cacheAdverbStorage.getAdverbsStorage().get(number);
    }

    @GetMapping("/getAllAdverbs")
    public Map<Integer, Word> getAllAdverbs() {
        return cacheAdverbStorage.adverbs;
    }

    @GetMapping("/getPlural/{number}")
    public Word getPlural(@PathVariable("number") Integer number) {
        return cachePluralStorage.getPluralsStorage().get(number);
    }

    @GetMapping("/getAllPlurals")
    public Map<Integer, Word> getAllPlurals() {
        return cachePluralStorage.plurals;
    }

    @GetMapping("/getException/{number}")
    public Word getException(@PathVariable("number") Integer number) {
        return cacheExceptionStorage.getExceptionsStorageWithText().get(number);
    }

    @GetMapping("/getAllExceptions")
    public Map<Integer, Word> getAllExceptions() {
        return cacheExceptionStorage.exceptionsStorageWithText;
    }
}
