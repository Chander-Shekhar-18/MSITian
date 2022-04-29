package com.example.msitian.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.msitian.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.chip.Chip;

import java.util.List;

public class NewsActivity extends AppCompatActivity implements SelectListener {

    ImageView btnBack;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    SpinKitView dialog;
    Chip chipScience, chipBusiness, chipGeneral, chipSports, chipHealth, chipEntertainment;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        btnBack = findViewById(R.id.btnBackNews);
        dialog = findViewById(R.id.waveNews);

        chipScience = findViewById(R.id.chipScience);
        chipScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chipScience.setChecked(true);
                chipBusiness.setChecked(false);
                chipGeneral.setChecked(false);
                chipSports.setChecked(false);
                chipHealth.setChecked(false);
                chipEntertainment.setChecked(false);

                String category = chipScience.getText().toString().trim();

                pd.setTitle("Fetching NEWS articles of " + category);
                pd.show();

                RequestManager manager = new RequestManager(NewsActivity.this);
                manager.getNewsHeadlines(listener, category, null);
            }
        });

        chipBusiness = findViewById(R.id.chipBusiness);
        chipBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chipScience.setChecked(false);
                chipBusiness.setChecked(true);
                chipGeneral.setChecked(false);
                chipSports.setChecked(false);
                chipHealth.setChecked(false);
                chipEntertainment.setChecked(false);

                String category = chipBusiness.getText().toString().trim();

                pd.setTitle("Fetching NEWS articles of " + category);
                pd.show();

                RequestManager manager = new RequestManager(NewsActivity.this);
                manager.getNewsHeadlines(listener, category, null);
            }
        });

        chipGeneral = findViewById(R.id.chipGeneral);
        chipGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chipScience.setChecked(false);
                chipBusiness.setChecked(false);
                chipGeneral.setChecked(true);
                chipSports.setChecked(false);
                chipHealth.setChecked(false);
                chipEntertainment.setChecked(false);

                String category = chipGeneral.getText().toString().trim();

                pd.setTitle("Fetching NEWS articles of " + category);
                pd.show();

                RequestManager manager = new RequestManager(NewsActivity.this);
                manager.getNewsHeadlines(listener, category, null);
            }
        });

        chipSports = findViewById(R.id.chipSports);
        chipSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chipScience.setChecked(false);
                chipBusiness.setChecked(false);
                chipGeneral.setChecked(false);
                chipSports.setChecked(true);
                chipHealth.setChecked(false);
                chipEntertainment.setChecked(false);

                String category = chipSports.getText().toString().trim();

                pd.setTitle("Fetching NEWS articles of " + category);
                pd.show();

                RequestManager manager = new RequestManager(NewsActivity.this);
                manager.getNewsHeadlines(listener, category, null);
            }
        });

        chipHealth = findViewById(R.id.chipHealth);
        chipHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chipScience.setChecked(false);
                chipBusiness.setChecked(false);
                chipGeneral.setChecked(false);
                chipSports.setChecked(false);
                chipHealth.setChecked(true);
                chipEntertainment.setChecked(false);

                String category = chipHealth.getText().toString().trim();

                pd.setTitle("Fetching NEWS articles of " + category);
                pd.show();

                RequestManager manager = new RequestManager(NewsActivity.this);
                manager.getNewsHeadlines(listener, category, null);
            }
        });

        chipEntertainment = findViewById(R.id.chipEntertainment);
        chipEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chipScience.setChecked(false);
                chipBusiness.setChecked(false);
                chipGeneral.setChecked(false);
                chipSports.setChecked(false);
                chipHealth.setChecked(false);
                chipEntertainment.setChecked(true);

                String category = chipEntertainment.getText().toString().trim();

                pd.setTitle("Fetching NEWS articles of " + category);
                pd.show();

                RequestManager manager = new RequestManager(NewsActivity.this);
                manager.getNewsHeadlines(listener, category, null);
            }
        });

        pd = new ProgressDialog(this);

        dialog.setVisibility(View.VISIBLE);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "technology", null);
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            showNews(list);
            recyclerView.setVisibility(View.VISIBLE);
            dialog.setVisibility(View.GONE);
            pd.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recyclerViewNews);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(NewsActivity.this, DetailNewsActivity.class)
                .putExtra("data", headlines));
    }

}


























