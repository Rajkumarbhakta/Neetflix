package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.TvSeriesListChildAdapter;
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.RetrofitInstance;
import com.rkbapps.neetflix.services.TvSeriesApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchSeriesResultFragment extends Fragment {

    public SearchSeriesResultFragment() {
        // Required empty public constructor
    }

    private static RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_series_result, container, false);
        String query = getArguments().getString("query");
        recyclerView=view.findViewById(R.id.recyclerSearchSeries);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        bindData(query);

        return view;
    }

    public static void bindData(String query){
        if(!query.equals("") || query!=null)
            loadSeriesSearchResult(query);
    }

    private static void loadSeriesSearchResult(String query){
        TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();

        Call<TvSeriesListModel> responseCall = tvSeriesApi.getSeriesSearchResult(ApiData.API_KEY,query,false);

        responseCall.enqueue(new Callback<TvSeriesListModel>() {
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {
                if(response.isSuccessful()){
                    recyclerView .setAdapter(new TvSeriesListChildAdapter(response.body().getResults(), recyclerView.getContext()));
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {

            }
        });


    }

}