package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.ParentAdapter;
import com.rkbapps.neetflix.models.MovieList;
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.RetrofitInstance;
import com.rkbapps.neetflix.services.TvSeriesApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TvSeriesFragment extends Fragment {

    private final List<MovieList> seriesLists = new ArrayList<>();
    private RecyclerView recyclerView;
    private ParentAdapter adapter;

    public TvSeriesFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_series, container, false);

        recyclerView = view.findViewById(R.id.recyclerTvSeries);


        loadPopularSeries();
        loadTrendingSeries();
        loadArrivingTodaySeries();
        loadTopRatedSeries();


        adapter = new ParentAdapter(seriesLists, getContext());


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }


    private void loadPopularSeries() {
        TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();
        Call<TvSeriesListModel> responseCall = tvSeriesApi.getPopularSeries(ApiData.API_KEY, 1);
        responseCall.enqueue(new Callback<TvSeriesListModel>() {
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {
                if (response.isSuccessful()) {
                    Log.d("respo", response + "");
                    seriesLists.add(new MovieList(MovieList.TV_SERIES, "Popular", response.body().getResults()));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {
                Log.d("respo", t + "");
            }
        });
    }

    private void loadTrendingSeries() {
        TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();
        Call<TvSeriesListModel> responseCall = tvSeriesApi.getTrendingSeries(ApiData.API_KEY, 1);
        responseCall.enqueue(new Callback<TvSeriesListModel>() {
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {
                if (response.isSuccessful()) {
                    //Log.d("repo",response+"");
                    seriesLists.add(new MovieList(MovieList.TV_SERIES, "Trending", response.body().getResults()));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {
                Log.d("repo", t + "");
            }
        });
    }

    private void loadTopRatedSeries() {
        TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();
        Call<TvSeriesListModel> responseCall = tvSeriesApi.getTopRatedSeries(ApiData.API_KEY, 1);
        responseCall.enqueue(new Callback<TvSeriesListModel>() {
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {
                if (response.isSuccessful()) {
                    //Log.d("repo",response+"");
                    seriesLists.add(new MovieList(MovieList.TV_SERIES, "Top Rated", response.body().getResults()));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {
                Log.d("repo", t + "");
            }
        });
    }

    private void loadArrivingTodaySeries() {
        TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();
        Call<TvSeriesListModel> responseCall = tvSeriesApi.getAiringTodaySeries(ApiData.API_KEY, 1);
        responseCall.enqueue(new Callback<TvSeriesListModel>() {
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {
                if (response.isSuccessful()) {
                    //Log.d("repo",response+"");
                    seriesLists.add(new MovieList(MovieList.TV_SERIES, "Airing Today", response.body().getResults()));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {
                Log.d("repo", t + "");
            }
        });
    }


}