package com.example.msitian.ebook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.msitian.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class PlacementAdapter extends RecyclerView.Adapter<PlacementAdapter.PlacementViewHolder>{

    private final Context context;
    private final List<PlacementData> list;

    public PlacementAdapter(Context context, List<PlacementData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PlacementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_item_layout, parent, false);
        return new PlacementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacementViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.pdfName.setText(list.get(position).getPlacementYear());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PdfViewerActivity.class);
                intent.putExtra("pdfUrl", list.get(position).getPlacementPdfUrl());
                context.startActivity(intent);
            }
        });

        holder.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(position).getPlacementPdfUrl()));
                context.startActivity(intent);
                Toast.makeText(context, "Pdf Downloaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PlacementViewHolder extends RecyclerView.ViewHolder {

        private final MaterialTextView pdfName;
        private final ImageView btnDownload;

        public PlacementViewHolder(@NonNull View itemView) {
            super(itemView);
            pdfName = itemView.findViewById(R.id.txtViewPdfName);
            btnDownload = itemView.findViewById(R.id.btnDownload);
        }
    }
}
