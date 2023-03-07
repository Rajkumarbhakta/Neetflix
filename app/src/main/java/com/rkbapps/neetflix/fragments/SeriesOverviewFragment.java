package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.CastAdapter;
import com.rkbapps.neetflix.adapter.CrewAdapter;
import com.rkbapps.neetflix.adapter.ProductionCompanyAdapter;
import com.rkbapps.neetflix.adapter.SeasonAdapter;
import com.rkbapps.neetflix.adapter.TvSeriesListChildAdapter;
import com.rkbapps.neetflix.models.castandcrew.CreditsModel;
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel;
import com.rkbapps.neetflix.models.tvseries.TvSeriesModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.RetrofitInstance;
import com.rkbapps.neetflix.services.TvSeriesApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SeriesOverviewFragment extends Fragment {

    private final TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();
    private TextView overview, status, type, firstAirDate, lastAirDate;
    private RecyclerView cast, crew, productionCompanies, similarSeries, network, seasons;
    public SeriesOverviewFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series_overview, container, false);
        int id = getArguments().getInt("id");
        overview = view.findViewById(R.id.txtSeriesOverview);
        status = view.findViewById(R.id.txtSeriesStatus);
        type = view.findViewById(R.id.txtSeriesType);
        firstAirDate = view.findViewById(R.id.txtFirstAirDate);
        lastAirDate = view.findViewById(R.id.lastAirDate);

        seasons = view.findViewById(R.id.recyclerSeasons);
        cast = view.findViewById(R.id.recyclerCastSeries);
        crew = view.findViewById(R.id.recyclerCrewSeries);
        productionCompanies = view.findViewById(R.id.recyclerProductionCompanySeries);
        similarSeries = view.findViewById(R.id.recyclerSimilarSeries);
        network = view.findViewById(R.id.recyclerNetwork);

        seasons.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        crew.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        cast.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        productionCompanies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        network.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        similarSeries.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        loadSeriesData(id);
        loadSeriesCredits(id);
        loadSimilarSeries(id);

        return view;
    }

    private void loadSeriesData(int id) {
        Call<TvSeriesModel> responseCall = tvSeriesApi.getSeriesDetails(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<TvSeriesModel>() {
            @Override
            public void onResponse(Call<TvSeriesModel> call, Response<TvSeriesModel> response) {
                if (response.isSuccessful()) {
                    TvSeriesModel tvSeriesModel = response.body();
                    overview.setText(tvSeriesModel.getOverview());
                    status.setText(tvSeriesModel.getStatus());
                    type.setText(tvSeriesModel.getType());
                    firstAirDate.setText(tvSeriesModel.getFirstAirDate());
                    lastAirDate.setText(tvSeriesModel.getLastAirDate());

                    seasons.setAdapter(new SeasonAdapter(getContext(), tvSeriesModel.getSeasons()));
                    network.setAdapter(new ProductionCompanyAdapter(getContext(), tvSeriesModel.getNetworks()));
                    productionCompanies.setAdapter(new ProductionCompanyAdapter(getContext(), tvSeriesModel.getProductionCompanies()));

                }
            }

            @Override
            public void onFailure(Call<TvSeriesModel> call, Throwable t) {

            }
        });
    }

    private void loadSeriesCredits(int id) {
        Call<CreditsModel> responseCall = tvSeriesApi.getSeriesCredits(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<CreditsModel>() {
            @Override
            public void onResponse(Call<CreditsModel> call, Response<CreditsModel> response) {
                if (response.isSuccessful()) {
                    cast.setAdapter(new CastAdapter(getContext(), response.body().getCast()));
                    crew.setAdapter(new CrewAdapter(getContext(), response.body().getCrew()));
                }
            }

            @Override
            public void onFailure(Call<CreditsModel> call, Throwable t) {

            }
        });
    }

    private void loadSimilarSeries(int id) {
        Call<TvSeriesListModel> responseCall = tvSeriesApi.getSimilarTvSeries(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<TvSeriesListModel>() {
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {
                if (response.isSuccessful()) {
                    similarSeries.setAdapter(new TvSeriesListChildAdapter(response.body().getResults(), getContext()));
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {

            }
        });
    }


}