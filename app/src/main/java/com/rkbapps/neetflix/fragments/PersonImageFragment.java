package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.PersonImageAdapter;
import com.rkbapps.neetflix.models.images.PersonImageModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.PersonApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonImageFragment extends Fragment {

    public PersonImageFragment() {
        // Required empty public constructor
    }
    private RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_image, container, false);
        int id = getArguments().getInt("id");

        recyclerView = view.findViewById(R.id.recyclerPersonImage);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        PersonApi personApi = RetrofitInstance.getPersonApi();
        Call<PersonImageModel>responseCall = personApi.getPersonImages(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<PersonImageModel>() {
            @Override
            public void onResponse(Call<PersonImageModel> call, Response<PersonImageModel> response) {
                if(response.isSuccessful())
                    recyclerView.setAdapter(new PersonImageAdapter(getContext(),response.body().getProfiles()));
            }

            @Override
            public void onFailure(Call<PersonImageModel> call, Throwable t) {

            }
        });


        return view;
    }
}