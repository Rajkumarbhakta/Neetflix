package com.rkbapps.neetflix.adapter;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.models.videos.Result;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final Context context;
    private final List<Result> videos;

    public VideoAdapter(Context context, List<Result> videos) {
        this.context = context;
        this.videos = videos;
    }

    public static void watchYoutubeVideo(Context context, String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(context).inflate(R.layout.video_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load("https://i.ytimg.com/vi/" + videos.get(position).getKey() + "/sddefault.jpg").into(holder.videoPoster);
        holder.videoName.setText(videos.get(position).getName());
        holder.videoSource.setText(videos.get(position).getSite());
        holder.videoType.setText(videos.get(position).getType());

        holder.videoPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchYoutubeVideo(context, videos.get(position).getKey());
            }
        });


    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView videoPoster;
        TextView videoName, videoSource, videoType;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            videoPoster = itemView.findViewById(R.id.imgVideoPoster);
            videoName = itemView.findViewById(R.id.txtVideoName);
            videoSource = itemView.findViewById(R.id.txtVideoSource);
            videoType = itemView.findViewById(R.id.txtVideoType);
        }
    }

}
