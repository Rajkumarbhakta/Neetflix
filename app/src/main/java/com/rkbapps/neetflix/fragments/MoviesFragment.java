package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.ParentAdapter;
import com.rkbapps.neetflix.models.MovieList;
import com.rkbapps.neetflix.models.movies.MovieListModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.MovieApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesFragment extends Fragment {

    private final List<MovieList> movieLists = new ArrayList<>();

    //private MovieListAdapter adapter;
    RecyclerView recyclerView;
    private ParentAdapter adapter;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        loadTrendingMovies();
        loadPopularMovies();
        loadTopRatedMovies();
        loadUpComingMovies();

        adapter = new ParentAdapter(movieLists, getContext());


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        return view;
    }

    private void loadPopularMovies() {
        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieListModel> responseCall = movieApi.getPopularMovies(ApiData.API_KEY, 1);
        responseCall.enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
                    movieLists.add(new MovieList(MovieList.MOVIE, "Popular", response.body().getResults(), 1));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {

            }
        });
    }

    private void loadTrendingMovies() {
        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieListModel> responseCall = movieApi.getTrendingMovies(ApiData.API_KEY, 1);
        responseCall.enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
                    movieLists.add(new MovieList(MovieList.MOVIE, "Trending", response.body().getResults(), 1));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {

            }
        });
    }

    private void loadTopRatedMovies() {
        //topRatedMovieList.clear();
        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieListModel> responseCall = movieApi.getTopRatedMovies(ApiData.API_KEY, 1);
        responseCall.enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
//                    topRatedMovieList = response.body().getResults();
//                    //adapter = new MovieListAdapter(popularMovieList,getContext());
//                    topRated.setAdapter(new MovieListAdapter(topRatedMovieList, getContext()));

                    movieLists.add(new MovieList(MovieList.MOVIE, "Top Rated", response.body().getResults(), 1));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {

            }
        });
    }

    private void loadLatestMovies() {
        //latestMovieList.clear();
        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieListModel> responseCall = movieApi.getLatestMovies(ApiData.API_KEY, 1);
        responseCall.enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
//                    latestMovieList = response.body().getResults();
//                    //adapter = new MovieListAdapter(popularMovieList,getContext());
//                    latest.setAdapter(new MovieListAdapter(latestMovieList, getContext()));
                    movieLists.add(new MovieList(MovieList.MOVIE, "Latest", response.body().getResults(), 1));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {

            }
        });
    }

    private void loadUpComingMovies() {
        //upComingMovieList.clear();
        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieListModel> responseCall = movieApi.getUpcomingMovies(ApiData.API_KEY, 1);
        responseCall.enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
//                    upComingMovieList = response.body().getResults();
//                    //adapter = new MovieListAdapter(popularMovieList,getContext());
//                    upComing.setAdapter(new MovieListAdapter(upComingMovieList, getContext()));
                    movieLists.add(new MovieList(MovieList.MOVIE, "Up Coming", response.body().getResults(), 1));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {

            }
        });
    }

}