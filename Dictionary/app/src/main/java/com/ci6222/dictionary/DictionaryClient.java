package com.ci6222.dictionary;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DictionaryClient {

    private static DictionaryClient instance = null;
    private DictionaryService dictionaryService;

    private DictionaryClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(DictionaryService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dictionaryService = retrofit.create(DictionaryService.class);
    }

    public static synchronized DictionaryClient getInstance() {
        if (instance == null) {
            instance = new DictionaryClient();
        }
        return instance;
    }

    public DictionaryService getDictionaryService() {
        return dictionaryService;
    }
}