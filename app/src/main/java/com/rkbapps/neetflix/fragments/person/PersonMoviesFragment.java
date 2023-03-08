package com.rkbapps.neetflix.fragments.person;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.person.PersonMoviesAdapter;
import com.rkbapps.neetflix.models.person.movie.WorkForMovies;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.PersonApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonMoviesFragment extends Fragment {

    private RecyclerView cast, crew;

    public PersonMoviesFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_movies, container, false);
        int id = getArguments().getInt("id");
        cast = view.findViewById(R.id.recyclerPersonMoviesAsCast);
        crew = view.findViewById(R.id.recyclerPersonMoviesAsCrew);

        cast.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        crew.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        PersonApi personApi = RetrofitInstance.getPersonApi();

        Call<WorkForMovies> responseCall = personApi.getPersonMovieCredits(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<WorkForMovies>() {
            @Override
            public void onResponse(Call<WorkForMovies> call, Response<WorkForMovies> response) {
                if (response.isSuccessful()) {
                    cast.setAdapter(new PersonMoviesAdapter(PersonMoviesAdapter.AS_CAST, getContext(), response.body().getCast(), 0));
                    crew.setAdapter(new PersonMoviesAdapter(PersonMoviesAdapter.AS_CREW, getContext(), response.body().getCrew()));
                }
            }

            @Override
            public void onFailure(Call<WorkForMovies> call, Throwable t) {

            }
        });


        return view;
    }
}