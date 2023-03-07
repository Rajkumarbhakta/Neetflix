package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.models.person.PersonDetails;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.PersonApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonPersonalInfoFragment extends Fragment {

    public PersonPersonalInfoFragment() {
        // Required empty public constructor
    }

    private TextView biography;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_personal_info, container, false);
        int id = getArguments().getInt("id");

        biography = view.findViewById(R.id.txtBiography);
        biography.setMovementMethod(new ScrollingMovementMethod());

        PersonApi personApi = RetrofitInstance.getPersonApi();
        Call<PersonDetails> responseCall = personApi.getPersonDetails(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<PersonDetails>() {
            @Override
            public void onResponse(Call<PersonDetails> call, Response<PersonDetails> response) {
                if (response.isSuccessful())
                    biography.setText(response.body().getBiography());
            }

            @Override
            public void onFailure(Call<PersonDetails> call, Throwable t) {

            }
        });

        return view;
    }
}