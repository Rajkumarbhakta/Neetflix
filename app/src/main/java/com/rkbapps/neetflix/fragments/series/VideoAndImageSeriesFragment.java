package com.rkbapps.neetflix.fragments.series;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.BackdropAdapter;
import com.rkbapps.neetflix.adapter.PosterAdapter;
import com.rkbapps.neetflix.adapter.VideoAdapter;
import com.rkbapps.neetflix.models.images.ImagesModel;
import com.rkbapps.neetflix.models.videos.VideoModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.RetrofitInstance;
import com.rkbapps.neetflix.services.TvSeriesApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideoAndImageSeriesFragment extends Fragment {
    TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();
    private RecyclerView backdrops, videos, posters;
    private TextView txtBackdrop, txtPoster, txtVideos;

    public VideoAndImageSeriesFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_and_image_series, container, false);
        int id = getArguments().getInt("id");

        backdrops = view.findViewById(R.id.recyclerSeriesBackdrops);
        videos = view.findViewById(R.id.recyclerSeriesVideos);
        posters = view.findViewById(R.id.recyclerSeriesPosters);

        txtBackdrop = view.findViewById(R.id.txtBackdropSeries);
        txtVideos = view.findViewById(R.id.txtVideosSeries);
        txtPoster = view.findViewById(R.id.txtPosterSeries);


        txtBackdrop.setVisibility(View.GONE);
        txtVideos.setVisibility(View.GONE);
        txtPoster.setVisibility(View.GONE);

        backdrops.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        posters.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        videos.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        loadSeriesImages(id);
        loadSeriesVideos(id);

        return view;
    }

    private void loadSeriesImages(int id) {
        Call<ImagesModel> responseCall = tvSeriesApi.getSeriesImages(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<ImagesModel>() {
            @Override
            public void onResponse(Call<ImagesModel> call, Response<ImagesModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body() != null) {
                            if (response.body().getPosters().size() != 0 && response.body().getPosters() != null) {
                                txtPoster.setVisibility(View.VISIBLE);
                                posters.setAdapter(new PosterAdapter(getContext(), response.body().getPosters()));
                            } else {
                                posters.setVisibility(View.GONE);
                                txtPoster.setVisibility(View.GONE);
                            }

                            if (response.body().getBackdrops().size() != 0 && response.body().getBackdrops() != null) {
                                txtBackdrop.setVisibility(View.VISIBLE);
                                backdrops.setAdapter(new BackdropAdapter(getContext(), response.body().getBackdrops()));
                            } else {
                                backdrops.setVisibility(View.GONE);
                                txtBackdrop.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ImagesModel> call, Throwable t) {

            }
        });
    }

    private void loadSeriesVideos(int id) {
        tvSeriesApi.getSeriesVideos(id, ApiData.API_KEY).enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null && response.body().getResults().size() != 0) {
                            txtVideos.setVisibility(View.VISIBLE);
                            videos.setAdapter(new VideoAdapter(getContext(), response.body().getResults()));
                        } else {
                            videos.setVisibility(View.GONE);
                            txtVideos.setVisibility(View.GONE);
                        }
                    }
                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {

            }
        });
    }


}