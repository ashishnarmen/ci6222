package com.ci6222.dictionary.client;


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
}