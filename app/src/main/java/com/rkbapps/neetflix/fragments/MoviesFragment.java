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

    //private RecyclerView popular, trending, topRated, latest, upComing;

//    private List<Result> popularMovieList = new ArrayList<>();
//    private List<Result> trendingMovieList = new ArrayList<>();
//
//    private List<Result> topRatedMovieList = new ArrayList<>();
//
//    private List<Result> latestMovieList = new ArrayList<>();
//
//    private List<Result> upComingMovieList = new ArrayList<>();

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
//        popular = view.findViewById(R.id.recyclerPopular);
//        trending = view.findViewById(R.id.recyclerTrending);
//        topRated = view.findViewById(R.id.recyclerTopRated);
//        latest = view.findViewById(R.id.recyclerLatest);
//        upComing = view.findViewById(R.id.recyclerUpcoming);
//
//        LinearLayoutManager lmTrending = new LinearLayoutManager(getContext());
//        lmTrending.setOrientation(RecyclerView.HORIZONTAL);
//        trending.setLayoutManager(lmTrending);
//
//        LinearLayoutManager lmPopular = new LinearLayoutManager(getContext());
//        lmPopular.setOrientation(RecyclerView.HORIZONTAL);
//        popular.setLayoutManager(lmPopular);
//
//        LinearLayoutManager lmTopRated = new LinearLayoutManager(getContext());
//        lmTopRated.setOrientation(RecyclerView.HORIZONTAL);
//        topRated.setLayoutManager(lmTopRated);
//
//
//        LinearLayoutManager lmLatest = new LinearLayoutManager(getContext());
//        lmLatest.setOrientation(RecyclerView.HORIZONTAL);
//        latest.setLayoutManager(lmLatest);
//
//
//        LinearLayoutManager lmUpcoming = new LinearLayoutManager(getContext());
//        lmUpcoming.setOrientation(RecyclerView.HORIZONTAL);
//        upComing.setLayoutManager(lmUpcoming);
//
//
//        loadTrendingMovies();
//
//        loadPopularMovies();
//
//        loadTopRatedMovies();
//
//        loadLatestMovies();
//
//        loadUpComingMovies();
        recyclerView = view.findViewById(R.id.recyclerView);
        loadTrendingMovies();
        loadPopularMovies();
        loadTopRatedMovies();
        //loadLatestMovies();
        loadUpComingMovies();

        adapter = new ParentAdapter(movieLists, getContext());


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        return view;
    }

    private void loadPopularMovies() {
        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieListModel> responseCall = movieApi.getPopularMovies(ApiData.API_KEY);
        responseCall.enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
//                    popularMovieList = response.body().getResults();
//                    //adapter = new MovieListAdapter(popularMovieList,getContext());
//                    popular.setAdapter(new MovieListAdapter(popularMovieList, getContext()));
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
        //trendingMovieList.clear();
        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieListModel> responseCall = movieApi.getTrendingMovies(ApiData.API_KEY);
        responseCall.enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
//                    trendingMovieList = response.body().getResults();
//                    //adapter = new MovieListAdapter(popularMovieList,getContext());
//                    trending.setAdapter(new MovieListAdapter(trendingMovieList, getContext()));
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
        Call<MovieListModel> responseCall = movieApi.getTopRatedMovies(ApiData.API_KEY);
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
        Call<MovieListModel> responseCall = movieApi.getLatestMovies(ApiData.API_KEY);
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
        Call<MovieListModel> responseCall = movieApi.getUpcomingMovies(ApiData.API_KEY);
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