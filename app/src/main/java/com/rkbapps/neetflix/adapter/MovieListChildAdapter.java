package com.rkbapps.neetflix.adapter;

import android.annotation.SuppressLint;
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
import com.rkbapps.neetflix.models.movies.MovieResult;

import java.util.ArrayList;
import java.util.List;

public class MovieListChildAdapter extends RecyclerView.Adapter<MovieListChildAdapter.MovieViewHolder> {

    private List<MovieResult> movieList = new ArrayList<>();
    private final Context context;

    public MovieListChildAdapter(List<MovieResult> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieListChildAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_child_items, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieListChildAdapter.MovieViewHolder holder, int position) {
        if (movieList.get(position).getPosterPath() != null) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + movieList.get(position).getPosterPath()).into(holder.poster);
        } else {
            Glide.with(context).load("").into(holder.poster);
        }

        holder.tittle.setText(movieList.get(position).getTitle());

        holder.releaseYear.setText(movieList.get(position).getReleaseDate());

        holder.ratting.setText("" + movieList.get(position).getVoteAverage());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView tittle, releaseYear, ratting;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.imgMoviePoster);
            tittle = itemView.findViewById(R.id.txtTittle);
            releaseYear = itemView.findViewById(R.id.txtReleaseYear);
            ratting = itemView.findViewById(R.id.txtRatting);
        }
    }
}
