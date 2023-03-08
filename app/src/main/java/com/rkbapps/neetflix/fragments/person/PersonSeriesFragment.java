package com.rkbapps.neetflix.fragments.person;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.person.PersonSeriesAdapter;
import com.rkbapps.neetflix.models.person.tvseries.WorkForSeries;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.PersonApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonSeriesFragment extends Fragment {

    public PersonSeriesFragment() {
        // Required empty public constructor
    }

    private RecyclerView cast,crew;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_series, container, false);
        int id = getArguments().getInt("id");
        cast = view.findViewById(R.id.recyclerPersonSeriesAsCast);
        crew= view.findViewById(R.id.recyclerPersonSeriesAsCrew);

        cast.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        crew.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        PersonApi personApi = RetrofitInstance.getPersonApi();

        Call<WorkForSeries> responseCall = personApi.getPersonSeriesCredits(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<WorkForSeries>() {
            @Override
            public void onResponse(Call<WorkForSeries> call, Response<WorkForSeries> response) {
                if(response.isSuccessful()){
                    cast.setAdapter(new PersonSeriesAdapter(PersonSeriesAdapter.AS_CAST,getContext(),response.body().getCast(), 1));
                    crew.setAdapter(new PersonSeriesAdapter(PersonSeriesAdapter.AS_CREW,getContext(),response.body().getCrew()));
                }
            }

            @Override
            public void onFailure(Call<WorkForSeries> call, Throwable t) {

            }
        });



        return view;
    }
}