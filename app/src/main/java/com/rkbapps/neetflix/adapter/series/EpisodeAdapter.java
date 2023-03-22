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
import com.rkbapps.neetflix.activityes.EpisodeDetailsActivity;
import com.rkbapps.neetflix.models.tvseries.seasons.EpisodeDetails;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.SeasonsDetailsViewHolder> {

    private final Context context;
    private final List<EpisodeDetails> episodeDetails;

    public EpisodeAdapter(Context context, List<EpisodeDetails> episodeDetails) {
        this.context = context;
        this.episodeDetails = episodeDetails;
    }

    @NonNull
    @Override
    public SeasonsDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SeasonsDetailsViewHolder(LayoutInflater.from(context).inflate(R.layout.episode_item_view, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SeasonsDetailsViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(episodeDetails.get(position).getStillPath()==null){
            Glide.with(context).load(R.drawable.general_backdrop).into(holder.stillImage);
        }else {
            Glide.with(context).load("https://image.tmdb.org/t/p/original/" + episodeDetails.get(position).getStillPath()).into(holder.stillImage);
        }

        holder.episodeNumber.setText("Episode "+episodeDetails.get(position).getEpisodeNumber());

        try {
            if(episodeDetails.get(position).getRuntime()/60 == 0){
                holder.runtime.setText(episodeDetails.get(position).getRuntime()+"M");
            }else{
                holder.runtime.setText(episodeDetails.get(position).getRuntime()/60+"H"+episodeDetails.get(position).getRuntime()%60+"M");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        holder.ratting.setText(""+episodeDetails.get(position).getVoteAverage());

        holder.episodeName.setText(episodeDetails.get(position).getName());

        Intent i = new Intent(context, EpisodeDetailsActivity.class);
        i.putExtra("episodeDetails",episodeDetails.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return episodeDetails.size();
    }

    public static class SeasonsDetailsViewHolder extends RecyclerView.ViewHolder {
        ImageView stillImage;
        TextView episodeNumber,runtime,ratting,episodeName;
        public SeasonsDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            stillImage = itemView.findViewById(R.id.imgStillImage);
            episodeName = itemView.findViewById(R.id.txtEpisodeName);
            episodeNumber = itemView.findViewById(R.id.txtEpisodeNumber);
            runtime = itemView.findViewById(R.id.txtEpisodeRuntime);
            ratting = itemView.findViewById(R.id.txtEpisodeRatting);

        }
    }
}
