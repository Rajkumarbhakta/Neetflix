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
import com.rkbapps.neetflix.models.castandcrew.Cast;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    private Context context;
    private List<Cast> casts;

    public CastAdapter(Context context, List<Cast> casts) {
        this.context = context;
        this.casts = casts;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CastViewHolder(LayoutInflater.from(context).inflate(R.layout.cast_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        holder.castName.setText(casts.get(position).getName());
        if(casts.get(position).getProfilePath()!=null)
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+casts.get(position).getProfilePath()).into(holder.cast);
        else
            Glide.with(context).load("").into(holder.cast);
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {
        ImageView cast;
        TextView castName;
        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            cast = itemView.findViewById(R.id.imgCast);
            castName = itemView.findViewById(R.id.txtCastName);
        }
    }
}
