package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.TvSeriesListChildAdapter;
import com.rkbapps.neetflix.db.SharedPreferanceValues;
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel;
import com.rkbapps.neetflix.models.tvseries.TvSeriesResult;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchSeriesResultFragment extends Fragment {

    private static final List<TvSeriesResult> results = new ArrayList<>();
    private static RecyclerView recyclerView;
    @SuppressLint("StaticFieldLeak")
    private static TextView txtNoSeries;
    private static TvSeriesListModel tvSeriesList = new TvSeriesListModel();
    @SuppressLint("StaticFieldLeak")
    private static TvSeriesListChildAdapter adapter;
    private static boolean isLoading = false;
    private static int page;
    private static String searchQuery;

    public SearchSeriesResultFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    public static void bindData(String query) {
        results.clear();
        if (!query.equals("dummyString") && query != null) {
            page = 1;
            searchQuery = query;
            loadSeriesSearchResult(query, page);
        } else
            txtNoSeries.setText("Try to search something.");
    }

    private static void loadSeriesSearchResult(String query, int page) {
        isLoading = true;
        RetrofitInstance.getTvSeriesApi()
                .getSeriesSearchResult(ApiData.API_KEY, query, page, SharedPreferanceValues.readNsfw(recyclerView.getContext()))
                .enqueue(new Callback<TvSeriesListModel>() {
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
                                tvSeriesList = response.body();
                                results.addAll(response.body().getResults());
                                adapter.notifyDataSetChanged();
                                isLoading = false;
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
        adapter = new TvSeriesListChildAdapter(results, getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);

        bindData(query);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

                if (gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() == results.size() - 1) {
                    //bottom of list!
                    if (!isLoading) {
                        page++;
                        if (tvSeriesList.getTotalPages() >= page) {
                            loadSeriesSearchResult(searchQuery, page);
                        }
                    }
                }
            }
        });
        return view;
    }
}