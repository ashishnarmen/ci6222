package com.ci6222.dictionary.client;

import com.ci6222.dictionary.model.DictionaryEntry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DictionaryInterface {

    String BASE_URL = "https://api.dictionaryapi.dev/";

    @GET("api/v2/entries/en/{word}")
    Call<List<DictionaryEntry>> getDictionaryEntries(@Path("word") String word);
}
