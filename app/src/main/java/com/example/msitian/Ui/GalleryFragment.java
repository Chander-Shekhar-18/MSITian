package com.example.msitian.Ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.msitian.R;
import com.example.msitian.adapters.GalleryAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    RecyclerView convoRecView, indeRecView, otherRecView;
    GalleryAdapter galleryAdapter;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);


        convoRecView = view.findViewById(R.id.convoRec);
        indeRecView = view.findViewById(R.id.independenceRec);
        otherRecView = view.findViewById(R.id.otherRec);

        reference = FirebaseDatabase.getInstance().getReference().child("gallery");

        getConvoImages();
        getIndependenceImages();
        getOtherImages();
        return view;
    }

    private void getIndependenceImages() {
        reference.child("Independence Day").addValueEventListener(new ValueEventListener() {
             List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String data = (String) snapshot1.getValue();
                    imageList.add(data);
                }
                galleryAdapter = new GalleryAdapter(getContext(),imageList);
                indeRecView.setLayoutManager(new GridLayoutManager(getContext(),3));
                indeRecView.setHasFixedSize(true);
                indeRecView.setAdapter(galleryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Some Error in Independence Day Images",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getOtherImages() {
        reference.child("Other Events").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String data = (String) snapshot1.getValue();
                    imageList.add(data);
                }
                galleryAdapter = new GalleryAdapter(getContext(),imageList);
                otherRecView.setHasFixedSize(true);
                otherRecView.setLayoutManager(new GridLayoutManager(getContext(),3));
                otherRecView.setAdapter(galleryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Some Error in Other Events Images",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getConvoImages() {
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {
             List<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String data = (String) snapshot1.getValue();
                    imageList.add(data);
                }
                galleryAdapter = new GalleryAdapter(getContext(),imageList);
                convoRecView.setLayoutManager(new GridLayoutManager(getContext(),3));
                convoRecView.setHasFixedSize(true);
                convoRecView.setAdapter(galleryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"Some Error in Convocation Images",Toast.LENGTH_SHORT).show();
            }
        });
    }
}