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
import com.rkbapps.neetflix.adapter.TvSeriesListChildAdapter;
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.RetrofitInstance;
import com.rkbapps.neetflix.services.TvSeriesApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchSeriesResultFragment extends Fragment {

    private static RecyclerView recyclerView;
    private static TextView txtNoSeries;

    public SearchSeriesResultFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    public static void bindData(String query) {
        if (!query.equals("dummyString") && query != null)
            loadSeriesSearchResult(query);
        else
            txtNoSeries.setText("Try to search something.");
    }

    private static void loadSeriesSearchResult(String query) {
        TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();

        Call<TvSeriesListModel> responseCall = tvSeriesApi.getSeriesSearchResult(ApiData.API_KEY, query, false);

        responseCall.enqueue(new Callback<TvSeriesListModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResults().size() == 0 || response.body().getResults() == null) {
                        recyclerView.setVisibility(View.GONE);
                        txtNoSeries.setText("No tv series found.");
                        txtNoSeries.setVisibility(View.VISIBLE);
                    } else {
                        txtNoSeries.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(new TvSeriesListChildAdapter(response.body().getResults(), recyclerView.getContext()));
                    }
                } else {
                    recyclerView.setVisibility(View.GONE);
                    txtNoSeries.setText(response.message());
                    txtNoSeries.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                txtNoSeries.setText(t.getMessage());
            }
        });


    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_series_result, container, false);
        String query = getArguments().getString("query");
        recyclerView = view.findViewById(R.id.recyclerSearchSeries);
        txtNoSeries = view.findViewById(R.id.txtSearchNoSeries);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        bindData(query);

        return view;
    }

}