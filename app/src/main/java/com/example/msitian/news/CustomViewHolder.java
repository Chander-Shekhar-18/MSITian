package com.example.msitian.news;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msitian.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    MaterialTextView textTitle, textSource;
    ImageView imgHeadline;
    MaterialCardView cardView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        textTitle = itemView.findViewById(R.id.text_title);
        textSource = itemView.findViewById(R.id.text_source);
        cardView = itemView.findViewById(R.id.main_container);
        imgHeadline = itemView.findViewById(R.id.image_headline);
    }
}
