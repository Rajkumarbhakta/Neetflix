package com.rkbapps.neetflix.adapter.series;

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
import com.rkbapps.neetflix.activityes.SeasonsDetailsActivity;
import com.rkbapps.neetflix.models.tvseries.Season;

import java.util.List;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder> {


    private final Context context;
    private final List<Season> seasonList;
    private final int tvID;

    public SeasonAdapter(Context context, List<Season> seasonList, int tvID) {
        this.context = context;
        this.seasonList = seasonList;
        this.tvID = tvID;
    }

    @NonNull
    @Override
    public SeasonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SeasonViewHolder(LayoutInflater.from(context).inflate(R.layout.season_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SeasonViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (seasonList.get(position).getPosterPath() != null) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + seasonList.get(position).getPosterPath()).into(holder.seasonPoster);
        } else {
            Glide.with(context).load("").into(holder.seasonPoster);
        }

        holder.seasonTitle.setText(seasonList.get(position).getName());

        holder.seasonReleaseDate.setText(seasonList.get(position).getAirDate());

        holder.seasonEpisode.setText("" + seasonList.get(position).getEpisodeCount() + " E");

        holder.seasonPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SeasonsDetailsActivity.class);
                i.putExtra("tvID", tvID);
                i.putExtra("seasonsNumber", seasonList.get(position).getSeasonNumber());
                i.putExtra("seasonsName", seasonList.get(position).getName());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return seasonList.size();
    }

    public class SeasonViewHolder extends RecyclerView.ViewHolder {
        ImageView seasonPoster;
        TextView seasonTitle, seasonEpisode, seasonReleaseDate;

        public SeasonViewHolder(@NonNull View itemView) {
            super(itemView);
            seasonPoster = itemView.findViewById(R.id.imgSeasonPoster);
            seasonTitle = itemView.findViewById(R.id.txtSeasonName);
            seasonEpisode = itemView.findViewById(R.id.txtSeasonTotalEpisode);
            seasonReleaseDate = itemView.findViewById(R.id.txtSeasonReleaseDate);
        }
    }
}
