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
    private TextView noCast, noCrew, noProductionCo, noSimilarSeries, noNetwork, noSeason;

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

        noCast = view.findViewById(R.id.txtNoCastDataSeries);
        noCrew = view.findViewById(R.id.txtNoCrewDataSeries);
        noProductionCo = view.findViewById(R.id.txtNoProductionCoDataSeries);
        noSimilarSeries = view.findViewById(R.id.txtNoSimilarSeries);
        noNetwork = view.findViewById(R.id.txtNoNetworkDataSeries);
        noSeason = view.findViewById(R.id.txtNoSeasonData);

        noCast.setVisibility(View.GONE);
        noCrew.setVisibility(View.GONE);
        noProductionCo.setVisibility(View.GONE);
        noNetwork.setVisibility(View.GONE);
        noSeason.setVisibility(View.GONE);
        noSimilarSeries.setVisibility(View.GONE);


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
                        if (tvSeriesModel.getSeasons().size() == 0 || tvSeriesModel.getSeasons() == null) {
                            noSeason.setVisibility(View.VISIBLE);
                        } else {
                            seasons.setAdapter(new SeasonAdapter(getContext(), tvSeriesModel.getSeasons(), id));
                        }

                        if (tvSeriesModel.getNetworks().size() == 0 || tvSeriesModel.getNetworks() == null) {
                            noNetwork.setVisibility(View.VISIBLE);
                        } else {
                            network.setAdapter(new ProductionCompanyAdapter(getContext(), tvSeriesModel.getNetworks()));
                        }

                        if (tvSeriesModel.getProductionCompanies().size() == 0 || tvSeriesModel.getProductionCompanies() == null) {
                            noProductionCo.setVisibility(View.VISIBLE);
                        } else {
                            productionCompanies.setAdapter(new ProductionCompanyAdapter(getContext(), tvSeriesModel.getProductionCompanies()));
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
                        if (response.body().getCast().size() == 0 || response.body().getCast() == null) {
                            noCast.setVisibility(View.VISIBLE);
                        } else {
                            cast.setAdapter(new CastAdapter(getContext(), response.body().getCast()));
                        }
                        if (response.body().getCrew().size() == 0 || response.body().getCrew() == null) {
                            noCrew.setVisibility(View.VISIBLE);
                        } else {
                            crew.setAdapter(new CrewAdapter(getContext(), response.body().getCrew()));
                        }
                    } else {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
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
                        if (response.body().getResults() == null || response.body().getResults().size() == 0) {
                            noSimilarSeries.setVisibility(View.VISIBLE);
                        } else {
                            similarSeries.setAdapter(new TvSeriesListChildAdapter(response.body().getResults(), getContext()));
                        }

                    } else {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
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