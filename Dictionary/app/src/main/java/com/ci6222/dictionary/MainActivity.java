package com.ci6222.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    EditText etWord;
    Button btnSearhWord;
    TextView tvMeaning;
    RequestQueue requestQueue;

    String baseUrl = "https://api.dictionaryapi.dev/api/v2/entries/en/";
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.etWord = (EditText) findViewById(R.id.word);
        this.btnSearhWord = (Button) findViewById(R.id.btn_search_word);
        this.tvMeaning = (TextView) findViewById(R.id.tv_meaning);
        this.tvMeaning.setMovementMethod(new ScrollingMovementMethod());

        requestQueue = Volley.newRequestQueue(this);
    }

    private void getMeaning(String word) {
        this.url = this.baseUrl + word;

        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObj = response.getJSONObject(i);
                            String repoName = jsonObj.get("word").toString();
                            String lastUpdated = jsonObj.get("origin").toString();
                            addToRepoList(repoName, lastUpdated);
                        } catch (JSONException e) {
                            Log.e("Volley", "Invalid JSON Object.");
                        }

                    }
                } else {
                    setRepoListText("No repos found.");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setRepoListText("Error while calling REST API");
                Log.e("Volley", error.toString());
            }
        });
        requestQueue.add(arrReq);
    }

    private void clearMeaning() {
        this.tvMeaning.setText("");
    }

    private void addToRepoList(String repoName, String lastUpdated) {
        String strRow = repoName + " / " + lastUpdated;
        String currentText = tvMeaning.getText().toString();
        this.tvMeaning.setText(currentText + "\n\n" + strRow);
    }

    private void setRepoListText(String str) {
        this.tvMeaning.setText(str);
    }

    public void searchWordClicked(View v) {
        clearMeaning();
        getMeaning(etWord.getText().toString());
    }
}
