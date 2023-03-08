package com.rkbapps.neetflix.adapter.person;

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
import com.rkbapps.neetflix.models.person.movie.AsACastModel;
import com.rkbapps.neetflix.models.person.movie.AsACrewModel;

import java.util.List;

public class PersonMoviesAdapter extends RecyclerView.Adapter<PersonMoviesAdapter.PersonMovieViewHolder> {

    public static final int AS_CAST = 0;
    public static final int AS_CREW = 1;
    private int type;
    private Context context;

    private List<AsACastModel> asACastModelList;
    private List<AsACrewModel> asACrewModelList;

    public PersonMoviesAdapter(int type, Context context, List<AsACastModel> asACastModelList,int i) {
        this.type = type;
        this.context = context;
        this.asACastModelList = asACastModelList;
    }

    public PersonMoviesAdapter(int type, Context context, List<AsACrewModel> asACrewModelList) {
        this.type = type;
        this.context = context;
        this.asACrewModelList = asACrewModelList;
    }

    @NonNull
    @Override
    public PersonMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PersonMovieViewHolder(LayoutInflater.from(context).inflate(R.layout.person_credit_item,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PersonMovieViewHolder holder, int position) {

        Intent i = new Intent(context, MoviePreviewActivity.class);
        if(type == AS_CAST){
            holder.tittle.setText(asACastModelList.get(position).getTitle());
            holder.date.setText(asACastModelList.get(position).getReleaseDate());
            holder.rating.setText(asACastModelList.get(position).getVoteAverage()+"");
            if (asACastModelList.get(position).getPosterPath() != null) {
                Glide.with(context).load("https://image.tmdb.org/t/p/w500" + asACastModelList.get(position).getPosterPath()).into(holder.poster);
            } else {
                Glide.with(context).load("").into(holder.poster);
            }
            if(!asACastModelList.get(position).getCharacter().equals(""))
                holder.description.setText("As "+asACastModelList.get(position).getCharacter());
            else
                holder.description.setText("---");

            if(asACastModelList.get(position).getAdult())
                holder.isNsfw.setVisibility(View.VISIBLE);
            else
                holder.isNsfw.setVisibility(View.INVISIBLE);

            i.putExtra("id", asACastModelList.get(position).getId());
            i.putExtra("tittle", asACastModelList.get(position).getTitle());
        }
        if(type == AS_CREW){
            holder.tittle.setText(asACrewModelList.get(position).getTitle());
            holder.date.setText(asACrewModelList.get(position).getReleaseDate());
            holder.rating.setText(asACrewModelList.get(position).getVoteAverage()+"");
            if (asACrewModelList.get(position).getPosterPath() != null) {
                Glide.with(context).load("https://image.tmdb.org/t/p/w500" + asACrewModelList.get(position).getPosterPath()).into(holder.poster);
            } else {
                Glide.with(context).load("").into(holder.poster);
            }
            if(!asACrewModelList.get(position).getJob().equals(""))
                holder.description.setText("As "+asACrewModelList.get(position).getJob());
            else
                holder.description.setText("---");

            if(asACrewModelList.get(position).getAdult())
                holder.isNsfw.setVisibility(View.VISIBLE);
            else
                holder.isNsfw.setVisibility(View.INVISIBLE);

            i.putExtra("id", asACrewModelList.get(position).getId());
            i.putExtra("tittle", asACrewModelList.get(position).getTitle());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(type == AS_CAST){
            return asACastModelList.size();
        }else
            return asACrewModelList.size();
    }

    public class PersonMovieViewHolder extends RecyclerView.ViewHolder {
        ImageView poster,isNsfw;
        TextView tittle,date,rating,description;
        public PersonMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.imgPersonItemPoster);
            tittle = itemView.findViewById(R.id.txtPersonItemTittle);;
            date = itemView.findViewById(R.id.txtPersonItemDate);
            rating = itemView.findViewById(R.id.txtPersonItemRatting);
            description = itemView.findViewById(R.id.txtPersonItemDescription);
            isNsfw = itemView.findViewById(R.id.imgPersonItemNsfw);
        }
    }
}