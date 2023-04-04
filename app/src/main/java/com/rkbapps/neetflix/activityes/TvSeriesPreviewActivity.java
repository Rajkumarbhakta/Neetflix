package com.rkbapps.neetflix.activityes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.GenreAdapter;
import com.rkbapps.neetflix.adapter.tab.TabLayoutAdapter;
import com.rkbapps.neetflix.db.Database;
import com.rkbapps.neetflix.db.DatabaseReference;
import com.rkbapps.neetflix.db.EntityModel;
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

    private ImageView backdrop, bookmark;

    private TvSeriesModel tvSeriesModel;

    private Database mDatabase;

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
        bookmark = findViewById(R.id.bookmarkSeries);

        mDatabase = DatabaseReference.getDatabase(getApplicationContext());

        recyclerGenre.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        if (mDatabase.getContentDao().isBookmarked(id)) {
            bookmark.setImageResource(R.drawable.bookmark);
        } else {
            bookmark.setImageResource(R.drawable.bookmark_border);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
                } else {
                    Toast.makeText(TvSeriesPreviewActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TvSeriesModel> call, Throwable t) {
                Toast.makeText(TvSeriesPreviewActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());


        TabLayoutAdapter tabAdapter = new TabLayoutAdapter(getSupportFragmentManager(), getApplicationContext(), tabLayout.getTabCount(), id, TabLayoutAdapter.SERIES);
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);


        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDatabase.getContentDao().isBookmarked(id)) {
                    mDatabase.getContentDao().removeBookmark(id);
                    Toast.makeText(TvSeriesPreviewActivity.this, "Removed from bookmark", Toast.LENGTH_SHORT).show();
                    bookmark.setImageResource(R.drawable.bookmark_border);

                } else {
                    responseCall.clone().enqueue(new Callback<TvSeriesModel>() {
                        @Override
                        public void onResponse(Call<TvSeriesModel> call, Response<TvSeriesModel> response) {
                            if (response.isSuccessful()) {
                                EntityModel entityModel = new EntityModel(EntityModel.SERIES, response.body().getAdult(), response.body().getId(), response.body().getPosterPath(), response.body().getFirstAirDate(), response.body().getName(), response.body().getVoteAverage());
                                mDatabase.getContentDao().addToBookmark(entityModel);
                                bookmark.setImageResource(R.drawable.bookmark);
                                Toast.makeText(TvSeriesPreviewActivity.this, "Added to bookmark", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(TvSeriesPreviewActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<TvSeriesModel> call, Throwable t) {
                            Toast.makeText(TvSeriesPreviewActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}