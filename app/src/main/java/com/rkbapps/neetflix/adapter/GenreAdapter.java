package com.rkbapps.neetflix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.models.Genre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    Context context;
    List<Genre> genres;

    public GenreAdapter(Context context, List<Genre> genres) {
        this.context = context;
        this.genres = genres;
    }

    @NonNull
    @Override
    public GenreAdapter.GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenreViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.GenreViewHolder holder, int position) {
        holder.genre.setText(genres.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public static class GenreViewHolder extends RecyclerView.ViewHolder {
        TextView genre;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            genre = itemView.findViewById(R.id.txtGenre);
        }
    }
}
