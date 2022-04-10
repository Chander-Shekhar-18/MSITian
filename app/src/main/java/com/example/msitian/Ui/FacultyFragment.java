package com.example.msitian.Ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.msitian.R;
import com.example.msitian.adapters.TeacherAdapter;
import com.example.msitian.adapters.TeacherData;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {

    private RecyclerView csDepartment, itDepartment, eceDepartment, eeeDepartment, appliedScienceDepartment;
    private LinearLayout csNoData, itNoData, eceNoData, eeeNoData, appliedScienceNoData;
    private List<TeacherData> csList, itList, eceList, eeeList, appliedScienceList;
    private TeacherAdapter adapter;
    NestedScrollView nestedScrollView;
    private DatabaseReference reference, dbRef;
    SpinKitView spinKitView;
    MaterialCardView csCard, itCard, eceCard, eeeCard, asCard;
    private Boolean isOpenRec = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_faculty, container, false);

        nestedScrollView = view.findViewById(R.id.nestedScrollView);
        spinKitView = view.findViewById(R.id.facultySpinKitView);
        csDepartment = view.findViewById(R.id.csDepartment);
        itDepartment = view.findViewById(R.id.itDepartment);
        eceDepartment = view.findViewById(R.id.eceDepartment);
        eeeDepartment = view.findViewById(R.id.eeeDepartment);
        appliedScienceDepartment = view.findViewById(R.id.appliedScienceDepartment);
        csNoData = view.findViewById(R.id.csNodata);
        itNoData = view.findViewById(R.id.itNodata);
        eceNoData = view.findViewById(R.id.eceNodata);
        eeeNoData = view.findViewById(R.id.eeeNodata);
        appliedScienceNoData = view.findViewById(R.id.appliedScienceNodata);
        csCard = view.findViewById(R.id.csCardView);
        itCard = view.findViewById(R.id.itCardView);
        eceCard = view.findViewById(R.id.eceCardView);
        eeeCard = view.findViewById(R.id.eeeCardView);
        asCard = view.findViewById(R.id.appliedScienceCardView);


        csCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpenRec) {
                    csDepartment.setVisibility(View.VISIBLE);
                    isOpenRec = false;
                } else {
                    csDepartment.setVisibility(View.GONE);
                    isOpenRec = true;
                }
            }
        });
        itCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpenRec) {
                    itDepartment.setVisibility(View.VISIBLE);
                    isOpenRec = false;
                } else {
                    itDepartment.setVisibility(View.GONE);
                    isOpenRec = true;
                }
            }
        });
        eceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpenRec) {
                    eceDepartment.setVisibility(View.VISIBLE);
                    isOpenRec = false;
                } else {
                    eceDepartment.setVisibility(View.GONE);
                    isOpenRec = true;
                }
            }
        });
        eeeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpenRec) {
                    eeeDepartment.setVisibility(View.VISIBLE);
                    isOpenRec = false;
                } else {
                    eeeDepartment.setVisibility(View.GONE);
                    isOpenRec = true;
                }
            }
        });
        asCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpenRec) {
                    appliedScienceDepartment.setVisibility(View.VISIBLE);
                    isOpenRec = false;
                } else {
                    appliedScienceDepartment.setVisibility(View.GONE);
                    isOpenRec = true;
                }
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("Teacher");

        csDepartment();

        itDepartment();

        eceDepartment();

        eeeDepartment();

        appliedScienceDepartment();

        return view;
    }

    private void appliedScienceDepartment() {
        dbRef = reference.child("Applied Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                appliedScienceList = new ArrayList<>();
                if (!snapshot.exists()) {
                    appliedScienceNoData.setVisibility(View.VISIBLE);
                    appliedScienceDepartment.setVisibility(View.GONE);
                } else {
                    appliedScienceNoData.setVisibility(View.GONE);
                    appliedScienceDepartment.setVisibility(View.GONE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        appliedScienceList.add(0, data);
                    }
                    appliedScienceDepartment.setHasFixedSize(true);
                    appliedScienceDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(appliedScienceList, getContext());
                    appliedScienceDepartment.setAdapter(adapter);
                    spinKitView.setVisibility(View.GONE);
                    nestedScrollView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eeeDepartment() {
        dbRef = reference.child("EEE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eeeList = new ArrayList<>();
                if (!snapshot.exists()) {
                    eeeNoData.setVisibility(View.VISIBLE);
                    eeeDepartment.setVisibility(View.GONE);
                } else {
                    eeeNoData.setVisibility(View.GONE);
                    eeeDepartment.setVisibility(View.GONE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        eeeList.add(0, data);
                    }
                    eeeDepartment.setHasFixedSize(true);
                    eeeDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(eeeList, getContext());
                    eeeDepartment.setAdapter(adapter);
                    spinKitView.setVisibility(View.GONE);
                    nestedScrollView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eceDepartment() {
        dbRef = reference.child("ECE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eceList = new ArrayList<>();
                if (!snapshot.exists()) {
                    eceNoData.setVisibility(View.VISIBLE);
                    eceDepartment.setVisibility(View.GONE);
                } else {
                    eceNoData.setVisibility(View.GONE);
                    eceDepartment.setVisibility(View.GONE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        eceList.add(0, data);
                    }
                    eceDepartment.setHasFixedSize(true);
                    eceDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(eceList, getContext());
                    eceDepartment.setAdapter(adapter);
                    spinKitView.setVisibility(View.GONE);
                    nestedScrollView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void itDepartment() {
        dbRef = reference.child("IT");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itList = new ArrayList<>();
                if (!snapshot.exists()) {
                    itNoData.setVisibility(View.VISIBLE);
                    itDepartment.setVisibility(View.GONE);
                } else {
                    itNoData.setVisibility(View.GONE);
                    itDepartment.setVisibility(View.GONE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        itList.add(0, data);
                    }
                    itDepartment.setHasFixedSize(true);
                    itDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(itList, getContext());
                    itDepartment.setAdapter(adapter);
                    spinKitView.setVisibility(View.GONE);
                    nestedScrollView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void csDepartment() {
        dbRef = reference.child("CSE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                csList = new ArrayList<>();
                if (!snapshot.exists()) {
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                } else {
                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.GONE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        csList.add(0, data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(csList, getContext());
                    csDepartment.setAdapter(adapter);
                    spinKitView.setVisibility(View.GONE);
                    nestedScrollView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}