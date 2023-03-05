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
import com.rkbapps.neetflix.models.castandcrew.Crew;

import java.util.List;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewViewHolder> {
    private final Context context;
    private final List<Crew> crews;

    public CrewAdapter(Context context, List<Crew> crews) {
        this.context = context;
        this.crews = crews;
    }

    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CrewViewHolder(LayoutInflater.from(context).inflate(R.layout.cast_item_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {
        holder.crewName.setText(crews.get(position).getName());
        if (crews.get(position).getJob() != null)
            holder.knownAs.setText(crews.get(position).getJob());
        else
            holder.knownAs.setText("");
        if (crews.get(position).getProfilePath() != null)
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + crews.get(position).getProfilePath()).into(holder.crew);
        else {
            if (crews.get(position).getGender() == 1)
                Glide.with(context).load(R.drawable.female_placeholder).into(holder.crew);
            else if (crews.get(position).getGender() == 2)
                Glide.with(context).load(R.drawable.male_placeholder).into(holder.crew);
            else
                Glide.with(context).load(R.drawable.male_placeholder).into(holder.crew);
        }
    }

    @Override
    public int getItemCount() {
        return crews.size();
    }

    public static class CrewViewHolder extends RecyclerView.ViewHolder {
        ImageView crew;
        TextView crewName, knownAs;

        public CrewViewHolder(@NonNull View itemView) {
            super(itemView);

            crew = itemView.findViewById(R.id.imgCast);
            crewName = itemView.findViewById(R.id.txtCastName);
            knownAs = itemView.findViewById(R.id.txtField);
        }
    }
}
