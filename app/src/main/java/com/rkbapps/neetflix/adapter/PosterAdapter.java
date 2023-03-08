package com.rkbapps.neetflix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rkbapps.neetflix.R;
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
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + posters.get(position).getFilePath()).into(holder.posterItem);
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
