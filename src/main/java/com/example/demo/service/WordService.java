package com.example.demo.service;

import com.example.demo.config.*;
import com.example.demo.model.Gender;
import com.example.demo.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class WordService {

    @Autowired
    private CacheStorage cacheStorage;

    @Autowired
    private CacheGenderStorage cacheGenderStorage;
    
    @Autowired
    private CacheAdverbStorage cacheAdverbStorage;

    @Autowired
    private CacheJustWordsStorage cacheJustWordsStorage;

    @Autowired
    private CachePluralStorage cachePluralStorage;

    @Autowired
    private CacheExceptionStorage cacheExceptionStorage;

    public static Integer number = 0;
    public static Integer numberManGenderStorage = 0;
    public static Integer numberWomanGenderStorage = 0;
    public static Integer numberMiddleGenderStorage = 0;
    public static Integer numberUnknown = 0;
    public static Integer numberAdverbs = 0;
    public static Integer numberPlural = 0;
    public static Integer numberExceptionStorage = 0;

    @EventListener(ApplicationReadyEvent.class)
    public void printHello() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("avidreaders.txt"), "UTF8"));
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                List<String> list = Arrays.asList(currentLine.split("\n"));
                for (String el : list) {
                    if (!el.isEmpty()) {
                        Word word = new Word();
                        word.setId(number);
                        number = number + 1;
                        word.setText(el);
                        cacheStorage.getStorage().put(word.getId(), word);
                    }
                }
            }

            reader.close();
            setAdverb();
            splitGender();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void splitGender() {
        for (Map.Entry<Integer, Word> el : cacheStorage.getStorage().entrySet()) {
            if (el.getValue().toString().contains(" ед. ")) {
                String name = firstWordInTerM(el);
                cachePluralStorage.plurals.put(numberPlural, new Word(numberPlural, name, el.getValue().getText()));
                numberPlural = numberPlural + 1;
                cacheJustWordsStorage.pluralStorageJustWords.put(name, name
                        +" ("+ inTheMiddleOfText(el.getValue(), " ед. ").replace(",","").toUpperCase()+")");
            } else {
                if (el.getValue().toString().contains(" м. ") && el.getValue().toString().contains(" ж. ")) {
                    String word = addToArray(el, Gender.BOTH);
                    cacheExceptionStorage.exceptionsStorageWithText.put(numberExceptionStorage, new Word(numberExceptionStorage, word, el.getValue().getText()));
                    numberExceptionStorage = numberExceptionStorage + 1;
                } else if (el.getValue().toString().contains(" м. ") && !el.getValue().toString().contains(" см. ")) {
                    String word = addToArray(el, Gender.MAN);
                    cacheGenderStorage.manGenderStorage.put(numberManGenderStorage, new Word(numberManGenderStorage, word, el.getValue().getText()));
                    numberManGenderStorage = numberManGenderStorage + 1;
                } else if (el.getValue().toString().contains(" ж. ") && (el.getValue().toString().contains(" сущ. ") || el.getValue().toString().contains(" сущ "))) {
                    String sendData = "";
                    if (el.getValue().toString().contains(" сущ. ")) {
                        sendData = " сущ. ";
                    }
                    if (el.getValue().toString().contains(" сущ ")) {
                        sendData = " сущ ";
                    }
                    String name = inTheMiddleOfText(el.getValue(), sendData).replace(",","").toUpperCase();
                    addToArrayJustWords(name, el.getValue().getText(), Gender.WOMAN);
                    cacheGenderStorage.womanGenderStorage.put(numberWomanGenderStorage, new Word(numberWomanGenderStorage, name, el.getValue().getText()));
                    numberWomanGenderStorage = numberWomanGenderStorage + 1;
                } else if (el.getValue().toString().contains(" ж. ") && !el.getValue().toString().contains(" сущ. ")) {
                    String word = addToArray(el, Gender.WOMAN);
                    cacheGenderStorage.womanGenderStorage.put(numberWomanGenderStorage, new Word(numberWomanGenderStorage, word, el.getValue().getText()));
                    numberWomanGenderStorage = numberWomanGenderStorage + 1;
                } else if (el.getValue().toString().contains(" ср. ")) {
                    String word = addToArray(el, Gender.MIDDLE);
                    cacheGenderStorage.middleGenderStorage.put(numberMiddleGenderStorage, new Word(numberMiddleGenderStorage, word, el.getValue().getText()));
                    numberMiddleGenderStorage = numberMiddleGenderStorage + 1;
                } else {
                    String word = addToArray(el, Gender.UNKNOWN);
                    cacheGenderStorage.unknown.put(numberUnknown, new Word(numberUnknown, word, el.getValue().getText()));
                    numberUnknown = numberUnknown + 1;
                }
            }
        }
    }

    private String firstWordInTerM(Map.Entry<Integer, Word> el) {
        String[] arr = el.toString().split(" ");
        String temp = arr[0];
        if (temp.contains("=")) {
            int index = temp.indexOf("=");
            temp = temp.substring(index+1);
        }
        return temp.replace(",", "");
    }

    private String addToArray(Map.Entry<Integer, Word> el, Gender gender) {
        String response = firstWordInTerM(el).toUpperCase();
        addToArrayJustWords(response, el.getValue().getText(), gender);
        return response;
    }

    private void addToArrayJustWords(String response, String text, Gender gender) {
        if (gender.equals(Gender.MAN)){
            cacheJustWordsStorage.manGenderStorageJustWords.put(response, text);
        }
        if (gender.equals(Gender.WOMAN)) {
            cacheJustWordsStorage.womanGenderStorageJustWords.put(response, text);
        }
        if (gender.equals(Gender.MIDDLE)) {
            cacheJustWordsStorage.middleGenderStorageJustWords.put(response, text);
        }
        if (gender.equals(Gender.UNKNOWN)) {
            cacheJustWordsStorage.unknownGenderStorageJustWords.put(response, text);
        }
        if (gender.equals(Gender.BOTH)) {
            cacheExceptionStorage.exceptionsStorage.put(response, text);
        }
    }

    private void setAdverb() {
        for (Map.Entry<Integer, Word> el : cacheStorage.getStorage().entrySet()) {
            if (el.getValue().toString().contains("прил.")) {
                cacheAdverbStorage.adverbs.put(numberAdverbs, el.getValue());
                numberAdverbs = numberAdverbs + 1;
                String name = inTheMiddleOfText(el.getValue(), "прил.").replace(",","").toUpperCase();
                cacheJustWordsStorage.adverbStorageJustWords.put(name, name);
            }
        }
    }

    private String inTheMiddleOfText(Word el, String name) {
        String[] arr = el.toString().split(" ");
        int index = 0;
        for (int i = 0; i<arr.length-1; i++) {
            if (arr[i].equals(name)) {
                index = i;
                break;
            }
        }
        String temp = arr[index+1];
        return temp;
    }
}
