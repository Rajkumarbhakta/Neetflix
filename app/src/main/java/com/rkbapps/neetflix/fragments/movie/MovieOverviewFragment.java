package com.rkbapps.neetflix.fragments.movie;

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
import com.rkbapps.neetflix.adapter.MovieListChildAdapter;
import com.rkbapps.neetflix.adapter.ProductionCompanyAdapter;
import com.rkbapps.neetflix.adapter.credit.CastAdapter;
import com.rkbapps.neetflix.adapter.credit.CrewAdapter;
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
    private final MovieApi movieApi = RetrofitInstance.getMovieApi();
    private TextView overview;
    private RecyclerView productionCompany, cast, similarMovies, crew;
    private TextView noCast, noCrew, noProductionCo, noSimilarMovies;

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

        noCast = view.findViewById(R.id.txtNoCastDataMovies);
        noCrew = view.findViewById(R.id.txtNoCrewDataMovies);
        noProductionCo = view.findViewById(R.id.txtNoProductionCoMovies);
        noSimilarMovies = view.findViewById(R.id.txtNoSimilarMovies);

        noCast.setVisibility(View.GONE);
        noCrew.setVisibility(View.GONE);
        noProductionCo.setVisibility(View.GONE);
        noSimilarMovies.setVisibility(View.GONE);

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
                    if (response.body() != null) {
                        overview.setText(response.body().getOverview());
                        if (response.body().getProductionCompanies() == null || response.body().getProductionCompanies().size() == 0) {
                            noProductionCo.setVisibility(View.VISIBLE);
                        } else {
                            productionCompany.setAdapter(new ProductionCompanyAdapter(getContext(), response.body().getProductionCompanies()));
                        }
                    } else {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadCredits(int id) {
        Call<CreditsModel> responseCall = movieApi.getMovieCredits(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<CreditsModel>() {
            @Override
            public void onResponse(Call<CreditsModel> call, Response<CreditsModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getCast().size() == 0 || response.body().getCast() == null) {
                            noCast.setVisibility(View.VISIBLE);
                        } else {
                            cast.setAdapter(new CastAdapter(getContext(), response.body().getCast()));
                        }
                        if (response.body().getCrew().size() == 0 || response.body().getCrew() == null) {
                            noCrew.setVisibility(View.VISIBLE);
                        } else {
                            crew.setAdapter(new CrewAdapter(getContext(), response.body().getCrew()));
                        }
                    } else {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreditsModel> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadSimilarMovies(int id) {
        Call<MovieListModel> responseCall = movieApi.getSimilarMovies(id, ApiData.API_KEY);

        responseCall.enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() == null || response.body().getResults().size() == 0) {
                            noSimilarMovies.setVisibility(View.VISIBLE);
                        } else {
                            similarMovies.setAdapter(new MovieListChildAdapter(response.body().getResults(), getContext()));
                        }
                    } else {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}