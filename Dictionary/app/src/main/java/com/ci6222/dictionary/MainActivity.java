package com.ci6222.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.android.volley.RequestQueue;
//import com.android.volley.VolleyError;
//import com.android.volley.Response;
//import com.android.volley.Request;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
import com.ci6222.dictionary.adapters.DictionaryEntryAdapter;
import com.ci6222.dictionary.client.DictionaryClient;
import com.ci6222.dictionary.model.DictionaryEntry;

import java.util.List;
import java.util.Stack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etWord;
    Button btnSearchWord;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Stack<String> searchedWords = new Stack<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.etWord = (EditText) findViewById(R.id.etWord);
        this.btnSearchWord = (Button) findViewById(R.id.btn_search_word);
        getMeaning(etWord.getText().toString());
    }

    @Override
    public void onBackPressed() {
        if (searchedWords.size() < 2) {
            super.onBackPressed();
        } else {
            searchedWords.pop();
            etWord.setText(searchedWords.pop());
            searchWordClicked(this.btnSearchWord);
        }

    }
    private void getMeaning(String word) {

        Call<List<DictionaryEntry>> call = DictionaryClient.getInstance().getDictionaryService().getDictionaryEntries(word);
        call.enqueue(new Callback<List<DictionaryEntry>>() {

            @Override
            public void onResponse(Call<List<DictionaryEntry>> call, Response<List<DictionaryEntry>> response) {
                List<DictionaryEntry> dictionaryEntryList = response.body();
                if (dictionaryEntryList == null) {
                    Toast.makeText(getApplicationContext(), "Unable to find the word", Toast.LENGTH_LONG).show();
                    return;
                }
                recyclerView = (RecyclerView) findViewById(R.id.dictionaryEntriesView);
                recyclerView.setHasFixedSize(true);
                Context context = getApplicationContext();
                layoutManager = new LinearLayoutManager(context);
                adapter = new DictionaryEntryAdapter(dictionaryEntryList,context) ;
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                searchedWords.add(word);
           }

            @Override
            public void onFailure(Call<List<DictionaryEntry>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
            }

        });
    }

    public void searchWordClicked(View v) {
        Utils.hideKeyboard(this);
        getMeaning(etWord.getText().toString());
    }
}
