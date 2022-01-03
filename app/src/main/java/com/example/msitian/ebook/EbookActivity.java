package com.example.msitian.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.msitian.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EbookActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private List<EbookData> list;
    private EbookAdapter adapter;
    ImageView btnBack;
    private EditText editTextSearch;
    SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        editTextSearch = findViewById(R.id.searchTextPdf);

        spinKitView = findViewById(R.id.waveEbook);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.eBookRecyclerView);
        reference = FirebaseDatabase.getInstance().getReference().child("pdf");

        getPdfData();
    }

    private void getPdfData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    EbookData data = dataSnapshot.getValue(EbookData.class);
                    list.add(0, data);
                }
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(EbookActivity.this));
                adapter = new EbookAdapter(EbookActivity.this, list);
                recyclerView.setAdapter(adapter);
                spinKitView.setVisibility(View.GONE);
                editTextSearch.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EbookActivity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<EbookData> filterList = new ArrayList<>();
        for (EbookData ebookData : list) {
            if (ebookData.getPdfTitle().toLowerCase().contains(editTextSearch.getText().toString().toLowerCase())) {
                filterList.add(ebookData);
            }
        }
        adapter.FilerList(filterList);
    }

}