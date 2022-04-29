package com.example.msitian.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.msitian.R;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

public class DetailNewsActivity extends AppCompatActivity {

    NewsHeadlines headlines;
    MaterialTextView txtViewTitle, txtViewAuthor, txtViewTime, txtViewDetail, txtViewContent;
    ImageView imageViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        txtViewTitle = findViewById(R.id.textDetailTitle);
        txtViewAuthor = findViewById(R.id.textViewDetailAuthor);
        txtViewTime = findViewById(R.id.textViewDetailTime);
        txtViewDetail = findViewById(R.id.textViewDetailText);
        txtViewContent = findViewById(R.id.txtViewDetailContent);

        imageViewNews = findViewById(R.id.imgDetailNews);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        txtViewTitle.setText(headlines.getTitle());
        txtViewAuthor.setText(headlines.getAuthor());
        txtViewTime.setText(headlines.getPublishedAt());
        txtViewDetail.setText(headlines.getDescription());
        txtViewContent.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(imageViewNews);
    }
}