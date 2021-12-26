package com.example.msitian.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msitian.FullImageActivity;
import com.example.msitian.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.noticeViewAdapter> {

    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public noticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_feed_item_layout, parent, false);
        return new noticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull noticeViewAdapter holder, int position) {
        NoticeData currentItem = list.get(position);

        holder.noticeTitle.setText(currentItem.getTitle());
        holder.noticeDate.setText(currentItem.getDate());
        holder.noticeTime.setText(currentItem.getTime());

        try {
            if (currentItem.getImage() != null)
                Picasso.get()
                        .load(currentItem.getImage())
                        .into(holder.noticeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.noticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullImageActivity.class);
                intent.putExtra("image", currentItem.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class noticeViewAdapter extends RecyclerView.ViewHolder {

        private final TextView noticeTitle, noticeDate, noticeTime;
        private final ImageView noticeImage;

        public noticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            noticeTitle = itemView.findViewById(R.id.noticeTitle);
            noticeImage = itemView.findViewById(R.id.noticeImage);
            noticeDate = itemView.findViewById(R.id.txtViewDate);
            noticeTime = itemView.findViewById(R.id.txtViewTime);
        }
    }

}
