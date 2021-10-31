package com.ci6222.dictionary;

import org.junit.Test;

import static org.junit.Assert.*;

import com.ci6222.dictionary.client.DictionaryClient;
import com.ci6222.dictionary.model.DictionaryEntry;

import java.io.IOException;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws IOException {
        DictionaryClient client = DictionaryClient.getInstance();
        List<DictionaryEntry> wordEntry =  DictionaryClient.getDictionaryEntries("word");
        assertEquals(wordEntry.get(0).word, "word");
        assertEquals(4, 2 + 2);
    }
}