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
import com.ci6222.dictionary.model.Meaning;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder> {

    private Context context;
    private List<Meaning> meaningItemList;
    public static  class MeaningViewHolder extends RecyclerView.ViewHolder {
        public TextView partOfSpeech;
        public RecyclerView definitions;

        public MeaningViewHolder(@NonNull View itemView) {
            super(itemView);
            partOfSpeech = itemView.findViewById(R.id.partOfSpeech);
            definitions = itemView.findViewById(R.id.definitionsView);
        }
    }

    public MeaningAdapter(List<Meaning> meaningItemList, Context context) {
        this.context = context;
        this.meaningItemList = meaningItemList;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meaning_item, parent, false);
        MeaningViewHolder dvh = new MeaningViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {
        Meaning currentItem = this.meaningItemList.get(position);
        holder.partOfSpeech.setText(currentItem.partOfSpeech);
        holder.definitions.setAdapter(new DefinitionAdapter(currentItem.definitions));
        holder.definitions.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        return meaningItemList.size();
    }
}
