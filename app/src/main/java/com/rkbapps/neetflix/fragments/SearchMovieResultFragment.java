package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.MovieListChildAdapter;
import com.rkbapps.neetflix.models.movies.MovieListModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.MovieApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import java.util.concurrent.ScheduledExecutorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchMovieResultFragment extends Fragment {


    public SearchMovieResultFragment() {
        // Required empty public constructor
    }

    private static RecyclerView recyclerView;
    static int totalPages;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_movie_result, container, false);
        String query = getArguments().getString("query");
        recyclerView=view.findViewById(R.id.recyclerSearchMovies);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        bindData(query);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//
//
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//
//
//            }
//        });

        return view;
    }
    public static void bindData(String query){
        if(!query.equals("") || query!=null) {
            loadMovieSearchResult(query);
        }
    }


    private static void loadMovieSearchResult(String query){
        MovieApi movieApi = RetrofitInstance.getMovieApi();

        Call<MovieListModel> responseCall = movieApi.getMovieSearchResult(ApiData.API_KEY,query,true);
        responseCall.enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if(response.isSuccessful()){
                    recyclerView.setAdapter(new MovieListChildAdapter(response.body().getResults(), recyclerView.getContext()));
                }
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {

            }
        });




    }

}