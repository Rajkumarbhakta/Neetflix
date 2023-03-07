package com.rkbapps.neetflix.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.activityes.TvSeriesPreviewActivity;
import com.rkbapps.neetflix.models.tvseries.TvSeriesResult;

import java.util.ArrayList;
import java.util.List;

public class TvSeriesListChildAdapter extends RecyclerView.Adapter<TvSeriesListChildAdapter.TvSeriesViewHolder> {

    private final Context context;
    private List<TvSeriesResult> seriesList = new ArrayList<>();

    public TvSeriesListChildAdapter(List<TvSeriesResult> seriesList, Context context) {
        this.seriesList = seriesList;
        this.context = context;
    }

    @NonNull
    @Override
    public TvSeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TvSeriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_child_items, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TvSeriesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (seriesList.get(position).getPosterPath() != null) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + seriesList.get(position).getPosterPath()).into(holder.poster);
        } else {
            Glide.with(context).load("").into(holder.poster);
        }

        holder.tittle.setText(seriesList.get(position).getName());

        holder.releaseYear.setText(seriesList.get(position).getFirstAirDate());

        holder.ratting.setText("" + seriesList.get(position).getVoteAverage());


        holder.nsfw.setVisibility(View.INVISIBLE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, TvSeriesPreviewActivity.class);
                i.putExtra("id", seriesList.get(position).getId());
                i.putExtra("tittle", seriesList.get(position).getName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }

    public static class TvSeriesViewHolder extends RecyclerView.ViewHolder {

        ImageView poster, nsfw;
        TextView tittle, releaseYear, ratting;

        public TvSeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.imgMoviePoster);
            tittle = itemView.findViewById(R.id.txtTittle);
            releaseYear = itemView.findViewById(R.id.txtReleaseYear);
            ratting = itemView.findViewById(R.id.txtRatting);
            nsfw = itemView.findViewById(R.id.imgNsfw);
        }
    }
}
