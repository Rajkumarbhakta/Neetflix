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
import com.rkbapps.neetflix.activityes.PersonActivity;
import com.rkbapps.neetflix.models.castandcrew.Cast;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    private final Context context;
    private final List<Cast> casts;

    public CastAdapter(Context context, List<Cast> casts) {
        this.context = context;
        this.casts = casts;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CastViewHolder(LayoutInflater.from(context).inflate(R.layout.cast_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.castName.setText(casts.get(position).getName());
        if (casts.get(position).getCharacter() != null)
            holder.character.setText(casts.get(position).getCharacter());
        else
            holder.character.setText("");

        if (casts.get(position).getProfilePath() != null)
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + casts.get(position).getProfilePath()).into(holder.cast);
        else {
            if (casts.get(position).getGender() == 1)
                Glide.with(context).load(R.drawable.female_placeholder).into(holder.cast);
            else if (casts.get(position).getGender() == 2)
                Glide.with(context).load(R.drawable.male_placeholder).into(holder.cast);
            else
                Glide.with(context).load(R.drawable.male_placeholder).into(holder.cast);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PersonActivity.class);
                i.putExtra("id",casts.get(position).getId());
                i.putExtra("name",casts.get(position).getName());
                i.putExtra("image",casts.get(position).getProfilePath());
                i.putExtra("gender",casts.get(position).getGender());
                i.putExtra("popularity",casts.get(position).getPopularity());
                i.putExtra("department",casts.get(position).getKnownForDepartment());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {
        ImageView cast;
        TextView castName, character;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            cast = itemView.findViewById(R.id.imgCast);
            castName = itemView.findViewById(R.id.txtCastName);
            character = itemView.findViewById(R.id.txtField);
        }
    }
}
