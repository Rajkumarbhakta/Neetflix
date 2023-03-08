package com.rkbapps.neetflix.fragments.movie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.BackdropAdapter;
import com.rkbapps.neetflix.adapter.PosterAdapter;
import com.rkbapps.neetflix.models.images.ImagesModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.MovieApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideoAndImageMovieFragment extends Fragment {

    MovieApi movieApi = RetrofitInstance.getMovieApi();
    private RecyclerView backdrops, videos, posters;


    public VideoAndImageMovieFragment() {

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_and_image_movie, container, false);
        int id = getArguments().getInt("id");
        backdrops = view.findViewById(R.id.recyclerMovieBackdrops);
        videos = view.findViewById(R.id.recyclerMovieVideos);
        posters = view.findViewById(R.id.recyclerMoviePosters);

        backdrops.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        posters.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        loadImages(id);
        return view;
    }

    private void loadImages(int id) {
        Call<ImagesModel> responseCall = movieApi.getMovieImages(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<ImagesModel>() {
            @Override
            public void onResponse(Call<ImagesModel> call, Response<ImagesModel> response) {
                if (response.isSuccessful()) {
                    posters.setAdapter(new PosterAdapter(getContext(), response.body().getPosters()));
                    backdrops.setAdapter(new BackdropAdapter(getContext(), response.body().getBackdrops()));
                }
            }

            @Override
            public void onFailure(Call<ImagesModel> call, Throwable t) {

            }
        });
    }
}