package com.rkbapps.neetflix.fragments.person;

import android.annotation.SuppressLint;
import android.graphics.Color;
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
import com.rkbapps.neetflix.adapter.person.PersonMoviesAdapter;
import com.rkbapps.neetflix.adapter.person.PersonSeriesAdapter;
import com.rkbapps.neetflix.models.person.tvseries.WorkForSeries;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.PersonApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonSeriesFragment extends Fragment {

    private RecyclerView cast, crew;
    private TextView asACast, asACrew;

    public PersonSeriesFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_series, container, false);
        int id = getArguments().getInt("id");
        cast = view.findViewById(R.id.recyclerPersonSeriesAsCast);
        crew = view.findViewById(R.id.recyclerPersonSeriesAsCrew);
        asACast = view.findViewById(R.id.txtPersonAsCastSeries);
        asACrew = view.findViewById(R.id.txtPersonAsCrewSeries);

        cast.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        crew.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        PersonApi personApi = RetrofitInstance.getPersonApi();

        Call<WorkForSeries> responseCall = personApi.getPersonSeriesCredits(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<WorkForSeries>() {
            @Override
            public void onResponse(Call<WorkForSeries> call, Response<WorkForSeries> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getCast().size() != 0 && response.body().getCast()!=null) {
                            cast.setAdapter(new PersonSeriesAdapter(PersonSeriesAdapter.AS_CAST, getContext(), response.body().getCast(), 1));
                        } else {
                            cast.setVisibility(View.GONE);
                            asACast.setVisibility(View.GONE);
                        }
                        if (response.body().getCrew().size() != 0 && response.body().getCrew()!=null) {
                            crew.setAdapter(new PersonSeriesAdapter(PersonSeriesAdapter.AS_CREW, getContext(), response.body().getCrew()));
                        } else {
                            crew.setVisibility(View.GONE);
                            asACrew.setVisibility(View.GONE);
                        }

                    } else {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<WorkForSeries> call, Throwable t) {
                asACast.setText("Error : " + t.getMessage());
                asACast.setTextColor(Color.WHITE);
            }
        });


        return view;
    }
}