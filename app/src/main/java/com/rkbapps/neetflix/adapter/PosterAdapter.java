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
import com.rkbapps.neetflix.models.images.Poster;

import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    private final Context context;
    private final List<Poster> posters;

    public PosterAdapter(Context context, List<Poster> posters) {
        this.context = context;
        this.posters = posters;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(context).inflate(R.layout.person_image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + posters.get(position).getFilePath()).into(holder.posterItem);
        holder.posterItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ImagePreviewActivity.class);
                i.putExtra("imageKey", posters.get(position).getFilePath());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posters.size();
    }

    public class PosterViewHolder extends RecyclerView.ViewHolder {
        ImageView posterItem;

        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            posterItem = itemView.findViewById(R.id.imgPersonImages);

        }
    }
}
