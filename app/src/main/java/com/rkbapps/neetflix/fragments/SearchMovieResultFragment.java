package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.MovieListChildAdapter;
import com.rkbapps.neetflix.models.movies.MovieListModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.MovieApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchMovieResultFragment extends Fragment {


    static int totalPages;
    private static RecyclerView recyclerView;
    private static TextView textNoMovie;

    public SearchMovieResultFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    public static void bindData(String query) {
        if (!query.equals("dummyString") && query != null) {
            loadMovieSearchResult(query);
        } else {
            textNoMovie.setText("Try to search something.");
        }
    }

    private static void loadMovieSearchResult(String query) {
        MovieApi movieApi = RetrofitInstance.getMovieApi();

        Call<MovieListModel> responseCall = movieApi.getMovieSearchResult(ApiData.API_KEY, query, false);
        responseCall.enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResults().size() == 0 || response.body().getResults() == null) {
                        recyclerView.setVisibility(View.GONE);
                        textNoMovie.setText("No movie found.");
                        textNoMovie.setVisibility(View.VISIBLE);
                    } else {
                        textNoMovie.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(new MovieListChildAdapter(response.body().getResults(), recyclerView.getContext()));
                    }
                } else {
                    recyclerView.setVisibility(View.GONE);
                    textNoMovie.setText(response.message());
                    textNoMovie.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                textNoMovie.setText(t.getMessage());
            }
        });


    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_movie_result, container, false);
        String query = getArguments().getString("query");
        recyclerView = view.findViewById(R.id.recyclerSearchMovies);
        textNoMovie = view.findViewById(R.id.txtSearchNoMovie);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));


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

}