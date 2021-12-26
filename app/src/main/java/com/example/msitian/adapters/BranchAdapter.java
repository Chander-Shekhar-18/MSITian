package com.example.msitian.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.msitian.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class BranchAdapter extends PagerAdapter {

    private final Context context;
    private final List<BranchModel> list;

    public BranchAdapter(Context context, List<BranchModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_layout_for_branch, container, false);


        MaterialTextView txtViewTitle, txtViewDesc;


        txtViewTitle = view.findViewById(R.id.txtViewBranchName);
        txtViewDesc = view.findViewById(R.id.txtViewBranchDescription);


        txtViewTitle.setText(list.get(position).getTitle());
        txtViewDesc.setText(list.get(position).getDescription());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
