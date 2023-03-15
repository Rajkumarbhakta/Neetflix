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
import com.rkbapps.neetflix.activityes.TvSeriesPreviewActivity;
import com.rkbapps.neetflix.models.person.tvseries.AsACastModel;
import com.rkbapps.neetflix.models.person.tvseries.AsACrewModel;

import java.util.List;

public class PersonSeriesAdapter extends RecyclerView.Adapter<PersonSeriesAdapter.PersonCreditViewHolder> {

    public static final int AS_CAST = 0;
    public static final int AS_CREW = 1;

    private final int type;
    private final Context context;
    private List<AsACastModel> asACastModelList;
    private List<AsACrewModel> asACrewModelList;

    public PersonSeriesAdapter(int type, Context context, List<AsACastModel> asACastModelList, int i) {
        this.type = type;
        this.context = context;
        this.asACastModelList = asACastModelList;
    }

    public PersonSeriesAdapter(int type, Context context, List<AsACrewModel> asACrewModelList) {
        this.type = type;
        this.context = context;
        this.asACrewModelList = asACrewModelList;
    }

    @NonNull
    @Override
    public PersonCreditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PersonCreditViewHolder(LayoutInflater.from(context).inflate(R.layout.person_credit_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PersonCreditViewHolder holder, int position) {
        Intent i = new Intent(context, TvSeriesPreviewActivity.class);
        if (type == AS_CAST) {
            holder.tittle.setText(asACastModelList.get(position).getName());
            holder.date.setText(asACastModelList.get(position).getFirstAirDate());
            holder.rating.setText(asACastModelList.get(position).getVoteAverage() + "");
            if (asACastModelList.get(position).getPosterPath() != null) {
                Glide.with(context).load("https://image.tmdb.org/t/p/w500" + asACastModelList.get(position).getPosterPath()).into(holder.poster);
            } else {
                Glide.with(context).load(R.drawable.default_poster).into(holder.poster);
            }
            if (!asACastModelList.get(position).getCharacter().equals(""))
                holder.description.setText("As " + asACastModelList.get(position).getCharacter() + " for " + asACastModelList.get(position).getEpisodeCount() + " episode.");
            else
                holder.description.setText("---");

            if (asACastModelList.get(position).getAdult())
                holder.isNsfw.setVisibility(View.VISIBLE);
            else
                holder.isNsfw.setVisibility(View.INVISIBLE);

            i.putExtra("id", asACastModelList.get(position).getId());
            i.putExtra("tittle", asACastModelList.get(position).getName());
        }
        if (type == AS_CREW) {
            holder.tittle.setText(asACrewModelList.get(position).getName());
            holder.date.setText(asACrewModelList.get(position).getFirstAirDate());
            holder.rating.setText(asACrewModelList.get(position).getVoteAverage() + "");
            if (asACrewModelList.get(position).getPosterPath() != null) {
                Glide.with(context).load("https://image.tmdb.org/t/p/w500" + asACrewModelList.get(position).getPosterPath()).into(holder.poster);
            } else {
                Glide.with(context).load("").into(holder.poster);
            }
            if (!asACrewModelList.get(position).getJob().equals(""))
                holder.description.setText("As " + asACrewModelList.get(position).getJob() + " for " + asACrewModelList.get(position).getEpisodeCount() + " episode.");
            else
                holder.description.setText("---");

            if (asACrewModelList.get(position).getAdult())
                holder.isNsfw.setVisibility(View.VISIBLE);
            else
                holder.isNsfw.setVisibility(View.INVISIBLE);

            i.putExtra("id", asACrewModelList.get(position).getId());
            i.putExtra("tittle", asACrewModelList.get(position).getName());
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
        if (type == AS_CAST)
            return asACastModelList.size();
        else
            return asACrewModelList.size();
    }

    public class PersonCreditViewHolder extends RecyclerView.ViewHolder {

        ImageView poster, isNsfw;
        TextView tittle, date, rating, description;

        public PersonCreditViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.imgPersonItemPoster);
            tittle = itemView.findViewById(R.id.txtPersonItemTittle);
            date = itemView.findViewById(R.id.txtPersonItemDate);
            rating = itemView.findViewById(R.id.txtPersonItemRatting);
            description = itemView.findViewById(R.id.txtPersonItemDescription);
            isNsfw = itemView.findViewById(R.id.imgPersonItemNsfw);
        }
    }
}
