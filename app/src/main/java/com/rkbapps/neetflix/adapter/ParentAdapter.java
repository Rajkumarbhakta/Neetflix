package com.rkbapps.neetflix.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.models.MovieList;

import java.util.ArrayList;
import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ParentViewHolder> {
    private final Context context;
    private List<MovieList> discoverRecycler = new ArrayList<>();

    public ParentAdapter(List<MovieList> discoverRecycler, Context context) {
        this.discoverRecycler = discoverRecycler;
        this.context = context;
    }


    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParentViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list_parent_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.type.setText(discoverRecycler.get(position).getType());
        holder.recyclerViewMovieList.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));

        if (discoverRecycler.get(position).getViewType() == MovieList.MOVIE) {
            holder.recyclerViewMovieList.setAdapter(new MovieListChildAdapter(discoverRecycler.get(position).getMovieList(), context));
        }
        if (discoverRecycler.get(position).getViewType() == MovieList.TV_SERIES) {
            holder.recyclerViewMovieList.setAdapter(new TvSeriesListChildAdapter(discoverRecycler.get(position).getTvSeriesList(), context));
        }

//        holder.seeMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, SeeMoreActivity.class);
//                i.putExtra("Type", discoverRecycler.get(position).getType());
//                i.putExtra("contentType", discoverRecycler.get(position).getViewType());
//                context.startActivity(i);
//            }
//        });


    }

    @Override
    public int getItemViewType(int position) {
        if (position == MovieList.MOVIE)
            return 0;
        else
            return 1;
    }

    @Override
    public int getItemCount() {
        return discoverRecycler.size();
    }

    public static class ParentViewHolder extends RecyclerView.ViewHolder {

        TextView type, seeMore;
        RecyclerView recyclerViewMovieList;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.txtType);
            seeMore = itemView.findViewById(R.id.txtSeeMore);
            recyclerViewMovieList = itemView.findViewById(R.id.recyclerMovieList);

        }
    }

}
