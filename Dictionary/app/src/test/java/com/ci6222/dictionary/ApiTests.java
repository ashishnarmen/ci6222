package com.ci6222.dictionary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.ci6222.dictionary.client.DictionaryClient;
import com.ci6222.dictionary.client.DictionaryInterface;
import com.ci6222.dictionary.model.DictionaryEntry;

import org.junit.Test;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ApiTests {
    DictionaryClient client = DictionaryClient.getInstance();
    DictionaryInterface service = client.getDictionaryService();

    private DictionaryEntry getDictionaryEntryForWord(String word) {
        DictionaryEntry entry = null;
        try {
            List<DictionaryEntry> entries = service.getDictionaryEntries(word).execute().body();
            if (entries != null){
                entry = entries.get(0);
            }
        } catch (Exception e) {
        }
        return entry;
    }
    @Test
    public void checkWord() {
        DictionaryEntry entry = getDictionaryEntryForWord("word");
        assertEquals(entry.word, "word");
    }
    @Test
    public void checkPhonetic() {
        String word = "hello";
        String expectedPhonetic = "həˈləʊ";
        DictionaryEntry entry = getDictionaryEntryForWord(word);
        String actualPhonetic = entry.phonetic;
        assertEquals(expectedPhonetic, actualPhonetic);
    }

    @Test
    public void checkMeaning() {
        String word = "run";
        String expectedDefinition = "move at a speed faster than a walk, never having both " +
                "or all the feet on the ground at the same time.";
        String expectedExample = "the dog ran across the road";
        String expectedSynonym = "sprint";
        DictionaryEntry entry = getDictionaryEntryForWord(word);
        String actualDefinition = entry.meanings.get(0).definitions.get(0).definition;
        String actualExample = entry.meanings.get(0).definitions.get(0).example;
        List<String> actualSynonyms = entry.meanings.get(0).definitions.get(0).synonyms;
        assertEquals(expectedExample, actualExample);
        assertEquals(expectedDefinition, actualDefinition);
        assertTrue(actualSynonyms.contains(expectedSynonym));
    }

    @Test
    public void checkInvalidWord() {
        String word = "alhjskha";
        DictionaryEntry entry = getDictionaryEntryForWord(word);
        assertNull(entry);
    }
}