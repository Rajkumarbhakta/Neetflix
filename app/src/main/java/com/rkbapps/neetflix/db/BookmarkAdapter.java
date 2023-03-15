package com.rkbapps.neetflix.db;

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
import com.rkbapps.neetflix.activityes.MoviePreviewActivity;
import com.rkbapps.neetflix.activityes.TvSeriesPreviewActivity;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookMarkViewHolder> {

    private Context context;
    private List<EntityModel> entityModelList;

    public BookmarkAdapter(Context context, List<EntityModel> entityModelList) {
        this.context = context;
        this.entityModelList = entityModelList;
    }

    @NonNull
    @Override
    public BookMarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookMarkViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_child_items, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookMarkViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (entityModelList.get(position).getPosterPath() != null) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + entityModelList.get(position).getPosterPath()).into(holder.poster);
        } else {
            Glide.with(context).load("").into(holder.poster);
        }

        holder.tittle.setText(entityModelList.get(position).getTitle());

        holder.releaseYear.setText(entityModelList.get(position).getReleaseDate());

        holder.ratting.setText("" + entityModelList.get(position).getVoteAverage());

        if (entityModelList.get(position).isAdult()) {
            holder.nsfw.setVisibility(View.VISIBLE);
        } else {
            holder.nsfw.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entityModelList.get(position).getType()==EntityModel.MOVIE){
                    Intent i = new Intent(context, MoviePreviewActivity.class);
                    i.putExtra("id", entityModelList.get(position).getId());
                    i.putExtra("tittle", entityModelList.get(position).getTitle());
                    context.startActivity(i);
                }
                if(entityModelList.get(position).getType()==EntityModel.SERIES){
                    Intent i = new Intent(context, TvSeriesPreviewActivity.class);
                    i.putExtra("id", entityModelList.get(position).getId());
                    i.putExtra("tittle", entityModelList.get(position).getTitle());
                    context.startActivity(i);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return entityModelList.size();
    }

    public static class BookMarkViewHolder extends RecyclerView.ViewHolder {
        ImageView poster, nsfw;
        TextView tittle, releaseYear, ratting;
        public BookMarkViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.imgMoviePoster);
            tittle = itemView.findViewById(R.id.txtTittle);
            releaseYear = itemView.findViewById(R.id.txtReleaseYear);
            ratting = itemView.findViewById(R.id.txtRatting);
            nsfw = itemView.findViewById(R.id.imgNsfw);
        }
    }
}
