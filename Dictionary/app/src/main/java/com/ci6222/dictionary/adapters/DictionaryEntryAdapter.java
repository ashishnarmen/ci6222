package com.ci6222.dictionary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ci6222.dictionary.R;
import com.ci6222.dictionary.model.DictionaryEntry;

import java.util.List;

public class DictionaryEntryAdapter extends RecyclerView.Adapter<DictionaryEntryAdapter.DictionaryEntryViewHolder> {
    private List<DictionaryEntry> dictionaryEntryItemList;
    private Context context;
    public static  class DictionaryEntryViewHolder extends RecyclerView.ViewHolder {
        public TextView word;
        public TextView origin;
        public RecyclerView phonetics;
        public RecyclerView meanings;

        public DictionaryEntryViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word);
            origin = itemView.findViewById(R.id.origin);
            phonetics = itemView.findViewById(R.id.phoneticView);
            meanings = itemView.findViewById(R.id.meaningsView);
        }
    }

    public DictionaryEntryAdapter(List<DictionaryEntry> dictionaryEntryItemList, Context context) {
        this.context = context;
        this.dictionaryEntryItemList = dictionaryEntryItemList;
    }

    @NonNull
    @Override
    public DictionaryEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dictionary_entry_item, parent, false);
        DictionaryEntryViewHolder dvh = new DictionaryEntryViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryEntryViewHolder holder, int position) {
        DictionaryEntry currentItem = this.dictionaryEntryItemList.get(position);
        holder.word.setText(currentItem.word);
        holder.origin.setText(currentItem.origin);
        holder.phonetics.setAdapter(new PhoneticAdapter(currentItem.phonetics));
        holder.phonetics.setLayoutManager(new LinearLayoutManager(context));
        holder.meanings.setAdapter(new MeaningAdapter(currentItem.meanings, context));
        holder.meanings.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        return dictionaryEntryItemList.size();
    }
}
