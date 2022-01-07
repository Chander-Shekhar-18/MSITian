package com.example.msitian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.squareup.picasso.Picasso;

public class FullImageActivity extends AppCompatActivity {

    String image;
    PhotoView imageView;
    SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        image = getIntent().getStringExtra("image");
        imageView = findViewById(R.id.fullImageView);
        spinKitView = findViewById(R.id.fullImageSpinKitView);

        Picasso.get().load(image).into(imageView);

        spinKitView.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
    }

}