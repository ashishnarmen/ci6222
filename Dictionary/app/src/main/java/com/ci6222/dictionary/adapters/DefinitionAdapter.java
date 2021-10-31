package com.ci6222.dictionary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ci6222.dictionary.R;
import com.ci6222.dictionary.model.Definition;

import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder> {

    private List<Definition> definitionItemList;
    public static  class DefinitionViewHolder extends RecyclerView.ViewHolder {
        public TextView definition;
        public TextView example;
        public TextView synonyms;
        public TextView antonyms;

        public DefinitionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.definition = itemView.findViewById(R.id.definition);
            this.example = itemView.findViewById(R.id.example);
            this.synonyms = itemView.findViewById(R.id.synonyms);
            this.antonyms = itemView.findViewById(R.id.antonyms);
        }
    }

    public DefinitionAdapter(List<Definition> definitionItemList) {
        this.definitionItemList = definitionItemList;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.definition_item, parent, false);
        DefinitionViewHolder dvh = new DefinitionViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        Definition currentItem = this.definitionItemList.get(position);
        holder.definition.setText(currentItem.definition);
        holder.example.setText(currentItem.example);
        holder.synonyms.setText(joinStrings(currentItem.synonyms, "Synonyms"));
        holder.antonyms.setText(joinStrings(currentItem.antonyms, "Antonyms"));
    }

    private String joinStrings(List<String> strings, String prefix) {
        String joinedString = "";
        if(strings.size() > 0) {
            joinedString = prefix + ": " + String.join(", ", strings);
        }
        return joinedString;
    }

    @Override
    public int getItemCount() {
        return definitionItemList.size();
    }
}
