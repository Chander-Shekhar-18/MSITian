package com.example.msitian.fees;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.msitian.R;
import com.example.msitian.adapters.BranchModel;
import com.example.msitian.adapters.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FeeStructure extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_structure);


       findViewById(R.id.feesBackBtn).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });

      TextView textView = findViewById(R.id.txtViewFeeTitle);
      textView.setText("Fees Structure");
    }
}