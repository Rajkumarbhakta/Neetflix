package com.rkbapps.neetflix.adapter.person;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.activityes.ImagePreviewActivity;
import com.rkbapps.neetflix.models.person.images.Profile;

import java.util.List;

public class PersonImageAdapter extends RecyclerView.Adapter<PersonImageAdapter.PersonImageViewHolder> {

    private final Context context;
    private final List<Profile> profileList;

    public PersonImageAdapter(Context context, List<Profile> profileList) {
        this.context = context;
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public PersonImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PersonImageViewHolder(LayoutInflater.from(context).inflate(R.layout.person_image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PersonImageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + profileList.get(position).getFilePath()).into(holder.imgPersonImage);
        holder.imgPersonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ImagePreviewActivity.class);
                i.putExtra("imageKey",profileList.get(position).getFilePath());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class PersonImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPersonImage;

        public PersonImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPersonImage = itemView.findViewById(R.id.imgPersonImages);
        }
    }
}
