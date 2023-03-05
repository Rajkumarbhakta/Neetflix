package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.CastAdapter;
import com.rkbapps.neetflix.adapter.CrewAdapter;
import com.rkbapps.neetflix.adapter.MovieListChildAdapter;
import com.rkbapps.neetflix.adapter.ProductionCompanyAdapter;
import com.rkbapps.neetflix.models.castandcrew.CreditsModel;
import com.rkbapps.neetflix.models.movies.MovieListModel;
import com.rkbapps.neetflix.models.movies.MovieModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.MovieApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieOverviewFragment extends Fragment {
    MovieApi movieApi = RetrofitInstance.getMovieApi();
    private TextView overview;
    private RecyclerView productionCompany, cast, similarMovies, crew;
    public MovieOverviewFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_overview, container, false);
        int id = getArguments().getInt("id");


        overview = view.findViewById(R.id.txtMovieOverview);
        cast = view.findViewById(R.id.recyclerCastMovie);
        crew = view.findViewById(R.id.recyclerCrewMovie);
        productionCompany = view.findViewById(R.id.recyclerProductionCompanyMovie);
        similarMovies = view.findViewById(R.id.recyclerSimilarMovies);

        crew.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        productionCompany.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        cast.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        similarMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));


        loadMovieDetails(id);
        loadCredits(id);
        loadSimilarMovies(id);


        return view;
    }


    private void loadMovieDetails(int id) {
        Call<MovieModel> responseCall = movieApi.getMovieDetails(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful()) {
                    overview.setText(response.body().getOverview());
                    productionCompany.setAdapter(new ProductionCompanyAdapter(getContext(), response.body().getProductionCompanies()));
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }


    private void loadCredits(int id) {
        Call<CreditsModel> responseCall = movieApi.getMovieCredits(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<CreditsModel>() {
            @Override
            public void onResponse(Call<CreditsModel> call, Response<CreditsModel> response) {
                if (response.isSuccessful()) {
                    crew.setAdapter(new CrewAdapter(getContext(), response.body().getCrew()));
                    Log.d("crew", "" + response.body().getCrew().get(0).toString());
                    cast.setAdapter(new CastAdapter(getContext(), response.body().getCast()));
                }
            }

            @Override
            public void onFailure(Call<CreditsModel> call, Throwable t) {

            }
        });
    }

    private void loadSimilarMovies(int id) {
        Call<MovieListModel> responseCall = movieApi.getSimilarMovies(id, ApiData.API_KEY);

        responseCall.enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
                    similarMovies.setAdapter(new MovieListChildAdapter(response.body().getResults(), getContext()));
                }
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {

            }
        });


    }


}