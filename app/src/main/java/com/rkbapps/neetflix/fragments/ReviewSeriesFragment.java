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
import com.rkbapps.neetflix.adapter.ReviewAdapter;
import com.rkbapps.neetflix.models.Review.ReviewModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.RetrofitInstance;
import com.rkbapps.neetflix.services.TvSeriesApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReviewSeriesFragment extends Fragment {


    public ReviewSeriesFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review_series, container, false);
        int id = getArguments().getInt("id");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerReviewSeries);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        TvSeriesApi api = RetrofitInstance.getTvSeriesApi();
        Call<ReviewModel> responseCall = api.getSeriesReview(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<ReviewModel>() {
            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                if (response.isSuccessful()) {
                    recyclerView.setAdapter(new ReviewAdapter(getContext(), response.body().getResults()));
                }
            }

            @Override
            public void onFailure(Call<ReviewModel> call, Throwable t) {

            }
        });


        return view;
    }
}