package com.ci6222.dictionary.client;


import com.ci6222.dictionary.model.DictionaryEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DictionaryClient {

    private static DictionaryClient instance = null;
    private DictionaryInterface dictionaryInterface;

    private DictionaryClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(DictionaryInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dictionaryInterface = retrofit.create(DictionaryInterface.class);
    }

    public static synchronized DictionaryClient getInstance() {
        if (instance == null) {
            instance = new DictionaryClient();
        }
        return instance;
    }

    public DictionaryInterface getDictionaryService() {
        return dictionaryInterface;
    }

    public static synchronized List<DictionaryEntry> getDictionaryEntries(String word) {
        List<DictionaryEntry> dictionaryEntries = new ArrayList<>();
        try {
            dictionaryEntries =  DictionaryClient.getInstance().getDictionaryService().getDictionaryEntries(word).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionaryEntries;
    }
}