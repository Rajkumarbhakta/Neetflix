package com.rkbapps.neetflix.activityes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.GenreAdapter;
import com.rkbapps.neetflix.adapter.TabLayoutAdapter;
import com.rkbapps.neetflix.models.tvseries.TvSeriesModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.RetrofitInstance;
import com.rkbapps.neetflix.services.TvSeriesApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvSeriesPreviewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView ratting, firstAirDate, originalLanguage, totalEpisodes, totalSeasons, tagLine;
    private RecyclerView recyclerGenre;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private ImageView backdrop;

    private TvSeriesModel tvSeriesModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_series_preview);
        int id = getIntent().getIntExtra("id", -1);
        String tittle = getIntent().getStringExtra("tittle");

        toolbar = findViewById(R.id.toolbarTvSeries);
        ratting = findViewById(R.id.txtRattingTvSeries);
        firstAirDate = findViewById(R.id.txtReleaseDateTvSeries);
        originalLanguage = findViewById(R.id.txtOriginalLanguageSeries);
        totalEpisodes = findViewById(R.id.txtNumberOfEpisodeSeries);
        totalSeasons = findViewById(R.id.txtTotalSeasons);
        tagLine = findViewById(R.id.txtTagLineTvSeries);
        recyclerGenre = findViewById(R.id.recyclerGenreTvSeries);
        viewPager = findViewById(R.id.pagerSeries);
        tabLayout = findViewById(R.id.tabLayoutSeries);
        backdrop = findViewById(R.id.imgBackdropTvSeries);

        recyclerGenre.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);


        TvSeriesApi api = RetrofitInstance.getTvSeriesApi();
        Call<TvSeriesModel> responseCall = api.getSeriesDetails(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<TvSeriesModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<TvSeriesModel> call, Response<TvSeriesModel> response) {
                if (response.isSuccessful()) {
                    tvSeriesModel = response.body();

                    ratting.setText("" + tvSeriesModel.getVoteAverage());
                    firstAirDate.setText(tvSeriesModel.getFirstAirDate());
                    originalLanguage.setText(tvSeriesModel.getOriginalLanguage());
                    totalEpisodes.setText(tvSeriesModel.getNumberOfEpisodes() + "E");
                    totalSeasons.setText(tvSeriesModel.getNumberOfSeasons() + "S");
                    tagLine.setText(tvSeriesModel.getTagline());
                    if (tvSeriesModel.getBackdropPath() != null) {
                        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500" + tvSeriesModel.getBackdropPath()).into(backdrop);
                    } else {
                        Glide.with(getApplicationContext()).load("").into(backdrop);
                    }
                    recyclerGenre.setAdapter(new GenreAdapter(getApplicationContext(), tvSeriesModel.getGenres()));
                }
            }

            @Override
            public void onFailure(Call<TvSeriesModel> call, Throwable t) {

            }
        });

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());


        TabLayoutAdapter tabAdapter = new TabLayoutAdapter(getSupportFragmentManager(), getApplicationContext(), tabLayout.getTabCount(), id, TabLayoutAdapter.SERIES);
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}