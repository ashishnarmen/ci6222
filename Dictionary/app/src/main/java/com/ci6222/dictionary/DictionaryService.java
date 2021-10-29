package com.ci6222.dictionary;

import com.ci6222.dictionary.model.DictionaryEntry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DictionaryService {

    String BASE_URL = "https://api.dictionaryapi.dev/";

    @GET("api/v2/entries/en/{word}")
    Call<List<DictionaryEntry>> getDictionaryEntry(@Path("word") String word);
}
