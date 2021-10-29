package com.ci6222.dictionary;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import com.ci6222.dictionary.model.DictionaryEntry;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws IOException {
        DictionaryClient client = DictionaryClient.getInstance();
        Call<List<DictionaryEntry>> wordEntryCall =  client.getDictionaryService().getDictionaryEntry("word");
        List<DictionaryEntry> wordEntry = wordEntryCall.execute().body();
        assertEquals(wordEntry.get(0).word, "word");
        assertEquals(4, 2 + 2);
    }
}