package com.rkbapps.neetflix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.models.tvseries.TvSeriesResult;

import java.util.ArrayList;
import java.util.List;

public class TvSeriesListChildAdapter extends RecyclerView.Adapter<TvSeriesListChildAdapter.TvSeriesViewHolder> {

    private List<TvSeriesResult> seriesList = new ArrayList<>();
    private final Context context;

    public TvSeriesListChildAdapter(List<TvSeriesResult> seriesList, Context context) {
        this.seriesList = seriesList;
        this.context = context;
    }

    @NonNull
    @Override
    public TvSeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TvSeriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_child_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TvSeriesViewHolder holder, int position) {
        if (seriesList.get(position).getPosterPath() != null) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + seriesList.get(position).getPosterPath()).into(holder.poster);
        } else {
            Glide.with(context).load("").into(holder.poster);
        }

        holder.tittle.setText(seriesList.get(position).getName());

        holder.releaseYear.setText(seriesList.get(position).getFirstAirDate());

        holder.ratting.setText("" + seriesList.get(position).getVoteAverage());
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    public static class TvSeriesViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView tittle, releaseYear, ratting;

        public TvSeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.imgMoviePoster);
            tittle = itemView.findViewById(R.id.txtTittle);
            releaseYear = itemView.findViewById(R.id.txtReleaseYear);
            ratting = itemView.findViewById(R.id.txtRatting);
        }
    }
}
