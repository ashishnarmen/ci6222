package com.ci6222.dictionary.adapters;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ci6222.dictionary.R;
import com.ci6222.dictionary.model.Phonetic;

import java.util.List;

public class PhoneticAdapter extends RecyclerView.Adapter<PhoneticAdapter.PhoneticViewHolder> {

    private List<Phonetic> PhoneticItemList;
    public static  class PhoneticViewHolder extends RecyclerView.ViewHolder {
        public TextView phoneticText;
        public ImageButton audio;

        public PhoneticViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneticText = itemView.findViewById(R.id.phonetic_text);
            audio = itemView.findViewById(R.id.audio);
        }
    }

    public PhoneticAdapter(List<Phonetic> PhoneticItemList) {
        this.PhoneticItemList = PhoneticItemList;
    }

    @NonNull
    @Override
    public PhoneticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.phonetic_item, parent, false);
        PhoneticViewHolder dvh = new PhoneticViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticViewHolder holder, int position) {
        Phonetic currentItem = this.PhoneticItemList.get(position);
        holder.phoneticText.setText(currentItem.text);
        if (currentItem.audio != null) {
            currentItem.audio = "https:"+currentItem.audio;
            holder.audio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioAttributes(
                            new AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build()
                    );
                    try {
                        mediaPlayer.setDataSource(currentItem.audio);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (Exception ex){
                        //Toast.makeText(MainActivity.this, "Unable to play sound", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            holder.audio.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return PhoneticItemList.size();
    }
}
