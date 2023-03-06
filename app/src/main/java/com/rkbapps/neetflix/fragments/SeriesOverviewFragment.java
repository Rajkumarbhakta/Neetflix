package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.CastAdapter;
import com.rkbapps.neetflix.adapter.CrewAdapter;
import com.rkbapps.neetflix.adapter.ProductionCompanyAdapter;
import com.rkbapps.neetflix.models.castandcrew.CreditsModel;
import com.rkbapps.neetflix.models.tvseries.TvSeriesModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.RetrofitInstance;
import com.rkbapps.neetflix.services.TvSeriesApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SeriesOverviewFragment extends Fragment {

    public SeriesOverviewFragment() {
        // Required empty public constructor
    }
    private final TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();

    private TextView overview,status,type,firstAirDate,lastAirDate;
    private RecyclerView cast,crew,productionCompanies,similarSeries;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_series_overview, container, false);
        int id = getArguments().getInt("id");
        overview = view.findViewById(R.id.txtSeriesOverview);
        status = view.findViewById(R.id.txtSeriesStatus);
        type= view.findViewById(R.id.txtSeriesType);
        firstAirDate = view.findViewById(R.id.txtFirstAirDate);
        lastAirDate = view.findViewById(R.id.lastAirDate);


        cast = view.findViewById(R.id.recyclerCastSeries);
        crew = view.findViewById(R.id.recyclerCrewSeries);
        productionCompanies = view.findViewById(R.id.recyclerProductionCompanySeries);
        similarSeries = view.findViewById(R.id.recyclerSimilarSeries);

        crew.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        cast.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        productionCompanies.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        similarSeries.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        loadSeriesData(id);
        loadSeriesCredits(id);

        return view;
    }

    private void loadSeriesData(int id){
        Call<TvSeriesModel> responseCall = tvSeriesApi.getSeriesDetails(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<TvSeriesModel>() {
            @Override
            public void onResponse(Call<TvSeriesModel> call, Response<TvSeriesModel> response) {
                if(response.isSuccessful()){
                    TvSeriesModel tvSeriesModel = response.body();
                    overview.setText(tvSeriesModel.getOverview());
                    status.setText(tvSeriesModel.getStatus());
                    type.setText(tvSeriesModel.getType());
                    firstAirDate.setText(tvSeriesModel.getFirstAirDate());
                    lastAirDate.setText(tvSeriesModel.getLastAirDate());
                    productionCompanies.setAdapter(new ProductionCompanyAdapter(getContext(),tvSeriesModel.getProductionCompanies()));
                }
            }
            @Override
            public void onFailure(Call<TvSeriesModel> call, Throwable t) {

            }
        });
    }

    private void loadSeriesCredits(int id){
        Call<CreditsModel> responseCall = tvSeriesApi.getSeriesCredits(id,ApiData.API_KEY);
        responseCall.enqueue(new Callback<CreditsModel>() {
            @Override
            public void onResponse(Call<CreditsModel> call, Response<CreditsModel> response) {
                if(response.isSuccessful()){
                    cast.setAdapter(new CastAdapter(getContext(),response.body().getCast()));
                    crew.setAdapter(new CrewAdapter(getContext(),response.body().getCrew()));
                }
            }
            @Override
            public void onFailure(Call<CreditsModel> call, Throwable t) {

            }
        });
    }


}