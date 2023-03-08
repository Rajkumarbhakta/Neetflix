package com.rkbapps.neetflix.fragments.series;

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
import com.rkbapps.neetflix.services.RetrofitInstance;
import com.rkbapps.neetflix.services.TvSeriesApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideoAndImageSeriesFragment extends Fragment {
    public VideoAndImageSeriesFragment() {
        // Required empty public constructor
    }
    private RecyclerView backdrops, videos, posters;
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

        backdrops.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        posters.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        loadSeriesImages(id);



        return view;
    }
    TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();
    private void loadSeriesImages(int id){
        Call<ImagesModel> responseCall = tvSeriesApi.getSeriesImages(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<ImagesModel>() {
            @Override
            public void onResponse(Call<ImagesModel> call, Response<ImagesModel> response) {
                if(response.isSuccessful()){
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