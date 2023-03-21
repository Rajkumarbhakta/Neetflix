package com.rkbapps.neetflix.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.activityes.ImagePreviewActivity;
import com.rkbapps.neetflix.models.images.Backdrop;

import java.util.List;

public class BackdropAdapter extends RecyclerView.Adapter<BackdropAdapter.BackdropViewHolder> {

    private final Context context;
    private final List<Backdrop> backdrops;

    public BackdropAdapter(Context context, List<Backdrop> backdrops) {
        this.context = context;
        this.backdrops = backdrops;
    }

    @NonNull
    @Override
    public BackdropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BackdropViewHolder(LayoutInflater.from(context).inflate(R.layout.backdrop_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BackdropViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + backdrops.get(position).getFilePath()).into(holder.backdropImg);

        holder.backdropImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ImagePreviewActivity.class);
                i.putExtra("imageKey",backdrops.get(position).getFilePath());
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return backdrops.size();
    }

    public class BackdropViewHolder extends RecyclerView.ViewHolder {
        ImageView backdropImg;

        public BackdropViewHolder(@NonNull View itemView) {
            super(itemView);
            backdropImg = itemView.findViewById(R.id.imgBackdropItemImage);
        }
    }
}
