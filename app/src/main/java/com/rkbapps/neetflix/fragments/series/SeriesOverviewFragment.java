package com.rkbapps.neetflix.fragments.series;

import android.annotation.SuppressLint;
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
import com.rkbapps.neetflix.adapter.ProductionCompanyAdapter;
import com.rkbapps.neetflix.adapter.TvSeriesListChildAdapter;
import com.rkbapps.neetflix.adapter.credit.CastAdapter;
import com.rkbapps.neetflix.adapter.credit.CrewAdapter;
import com.rkbapps.neetflix.adapter.series.SeasonAdapter;
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
    private TextView txtCast, txtCrew, txtProductionCo, txtSimilarSeries, txtNetwork, txtSeason;

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


        txtCast = view.findViewById(R.id.txtCastSeriesOverview);
        txtCrew = view.findViewById(R.id.txtCrewSeriesOverview);
        txtNetwork = view.findViewById(R.id.txtNetworkSeriesOverview);
        txtProductionCo = view.findViewById(R.id.txtProductionCompanySeriesOverview);
        txtSeason = view.findViewById(R.id.txtSeasons);
        txtSimilarSeries = view.findViewById(R.id.txtSimilarSeries);


        txtCast.setVisibility(View.GONE);
        txtCrew.setVisibility(View.GONE);
        txtNetwork.setVisibility(View.GONE);
        txtProductionCo.setVisibility(View.GONE);
        txtSeason.setVisibility(View.GONE);
        txtSimilarSeries.setVisibility(View.GONE);


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
                    if (response.body() == null) {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    } else {
                        TvSeriesModel tvSeriesModel = response.body();
                        overview.setText(tvSeriesModel.getOverview());
                        status.setText(tvSeriesModel.getStatus());
                        type.setText(tvSeriesModel.getType());
                        firstAirDate.setText(tvSeriesModel.getFirstAirDate());
                        lastAirDate.setText(tvSeriesModel.getLastAirDate());
                        if (tvSeriesModel.getSeasons().size() != 0 && tvSeriesModel.getSeasons() != null) {
                            txtSeason.setVisibility(View.VISIBLE);
                            seasons.setAdapter(new SeasonAdapter(getContext(), tvSeriesModel.getSeasons(), id));
                        } else {
                            seasons.setVisibility(View.GONE);
                        }

                        if (tvSeriesModel.getNetworks().size() != 0 && tvSeriesModel.getNetworks() != null) {
                            txtNetwork.setVisibility(View.VISIBLE);
                            network.setAdapter(new ProductionCompanyAdapter(getContext(), tvSeriesModel.getNetworks()));
                        } else {
                            network.setVisibility(View.GONE);
                        }

                        if (tvSeriesModel.getProductionCompanies().size() != 0 && tvSeriesModel.getProductionCompanies() != null) {
                            txtProductionCo.setVisibility(View.VISIBLE);
                            productionCompanies.setAdapter(new ProductionCompanyAdapter(getContext(), tvSeriesModel.getProductionCompanies()));
                        } else {
                            productionCompanies.setVisibility(View.GONE);
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TvSeriesModel> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadSeriesCredits(int id) {
        Call<CreditsModel> responseCall = tvSeriesApi.getSeriesCredits(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<CreditsModel>() {
            @Override
            public void onResponse(Call<CreditsModel> call, Response<CreditsModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getCast().size() != 0 && response.body().getCast() != null) {
                            txtCast.setVisibility(View.VISIBLE);
                            cast.setAdapter(new CastAdapter(getContext(), response.body().getCast()));
                        } else {
                            txtCast.setVisibility(View.GONE);
                            cast.setVisibility(View.GONE);
                        }
                        if (response.body().getCrew().size() != 0 && response.body().getCrew() != null) {
                            txtCrew.setVisibility(View.VISIBLE);
                            crew.setAdapter(new CrewAdapter(getContext(), response.body().getCrew()));
                        } else {
                            crew.setVisibility(View.GONE);
                            txtCrew.setVisibility(View.GONE);
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreditsModel> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadSimilarSeries(int id) {
        Call<TvSeriesListModel> responseCall = tvSeriesApi.getSimilarTvSeries(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<TvSeriesListModel>() {
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResults() != null && response.body().getResults().size() != 0) {
                            txtSimilarSeries.setVisibility(View.VISIBLE);
                            similarSeries.setAdapter(new TvSeriesListChildAdapter(response.body().getResults(), getContext()));
                        } else {
                            similarSeries.setVisibility(View.GONE);
                            txtSimilarSeries.setVisibility(View.GONE);
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}