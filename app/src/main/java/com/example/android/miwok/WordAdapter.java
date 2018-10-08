package com.example.android.miwok;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sansriti on 24-07-2018.
 */

public class WordAdapter extends ArrayAdapter<Word>{
    int colorResourceId;

    public WordAdapter(Context context, ArrayList<Word> words, int color){

        super(context,0,words);
        colorResourceId=color;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
    }
    Word currentWord = getItem(position);
        TextView miwok_word = (TextView)listItemView.findViewById(R.id.miwok_text_view);
        miwok_word.setText(currentWord.getMiwokTranslation());
        TextView default_word=(TextView)listItemView.findViewById(R.id.default_text_view);
        default_word.setText(currentWord.getDefaultTranslation());
        ImageView image_view=(ImageView)listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()){
            image_view.setImageResource(currentWord.getImageResourceId());
        image_view.setVisibility(View.VISIBLE);}
        else {
            image_view.setVisibility(View.GONE);
        }
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), colorResourceId);
        textContainer.setBackgroundColor(color);
        return listItemView;}}
