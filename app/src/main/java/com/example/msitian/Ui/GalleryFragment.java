package com.example.msitian.Ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.msitian.R;
import com.example.msitian.adapters.GalleryAdapter;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    RecyclerView campusRecView, seminarRecView, eventsRecView;
    GalleryAdapter galleryAdapter;
    DatabaseReference reference;
    NestedScrollView nestedScrollView;
    SpinKitView spinKitView;
    MaterialCardView campusCard, seminarCard, eventsCard;
    private Boolean isOpenRec = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        campusRecView = view.findViewById(R.id.campusRecyclerView);
        seminarRecView = view.findViewById(R.id.seminarRecyclerView);
        eventsRecView = view.findViewById(R.id.eventsRecyclerView);
        nestedScrollView = view.findViewById(R.id.galleryScrollView);
        spinKitView = view.findViewById(R.id.gallerySpinKitView);
        campusCard = view.findViewById(R.id.campusCardView);
        seminarCard = view.findViewById(R.id.seminarCardView);
        eventsCard = view.findViewById(R.id.eventsCardView);

        campusCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpenRec){
                    campusRecView.setVisibility(View.VISIBLE);
                    isOpenRec = false;
                }else {
                    campusRecView.setVisibility(View.GONE);
                    isOpenRec = true;
                }
            }
        });
        seminarCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpenRec){
                    seminarRecView.setVisibility(View.VISIBLE);
                    isOpenRec = false;
                }else {
                    seminarRecView.setVisibility(View.GONE);
                    isOpenRec = true;
                }
            }
        });
        eventsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpenRec){
                    eventsRecView.setVisibility(View.VISIBLE);
                    isOpenRec = false;
                }else {
                    eventsRecView.setVisibility(View.GONE);
                    isOpenRec = true;
                }
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("gallery");


        getCampusImages();
        getSeminarImages();
        getEventsImages();

        return view;
    }

    private void getCampusImages() {
        reference.child("Campus").addValueEventListener(new ValueEventListener() {
            final List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String data = (String) snapshot1.getValue();
                    imageList.add(data);
                }
                galleryAdapter = new GalleryAdapter(getContext(), imageList);
                campusRecView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                campusRecView.setHasFixedSize(true);
                campusRecView.setAdapter(galleryAdapter);
                spinKitView.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Some Error in Campus Images", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getEventsImages() {
        reference.child("Events").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String data = (String) snapshot1.getValue();
                    imageList.add(data);
                }
                galleryAdapter = new GalleryAdapter(getContext(), imageList);
                eventsRecView.setHasFixedSize(true);
                eventsRecView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                eventsRecView.setAdapter(galleryAdapter);
                spinKitView.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Some Error in Events Images", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSeminarImages() {
        reference.child("Seminar").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String data = (String) snapshot1.getValue();
                    imageList.add(data);
                }
                galleryAdapter = new GalleryAdapter(getContext(), imageList);
                seminarRecView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                seminarRecView.setHasFixedSize(true);
                seminarRecView.setAdapter(galleryAdapter);
                spinKitView.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Some Error in Seminar Images", Toast.LENGTH_SHORT).show();
            }
        });
    }
}