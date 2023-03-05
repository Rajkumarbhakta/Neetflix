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
import com.rkbapps.neetflix.fragments.MovieOverviewFragment;
import com.rkbapps.neetflix.models.Genre;
import com.rkbapps.neetflix.models.movies.MovieModel;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.MovieApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePreviewActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MovieModel movieModel = new MovieModel();
    private RecyclerView recyclerView;
    private final List<Genre> genreList = new ArrayList<>();
    private TextView rating, budget, revenue, releaseDate, runtime, tagLine;
    private ImageView backdrop;
    private Toolbar toolbar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_preview);
        int id = getIntent().getIntExtra("id", -1);
        String tittle = getIntent().getStringExtra("tittle");

        tabLayout = findViewById(R.id.tabLayoutMovie);
        viewPager = findViewById(R.id.pagerMovie);
        recyclerView = findViewById(R.id.recyclerGenre);
        rating = findViewById(R.id.txtRattingMovie);
        budget = findViewById(R.id.txtBudgetMovie);
        revenue = findViewById(R.id.txtRevenueMovie);
        releaseDate = findViewById(R.id.txtReleaseDateMovie);
        runtime = findViewById(R.id.txtRuntime);
        backdrop = findViewById(R.id.imgBackdropMovie);
        tagLine = findViewById(R.id.txtTagLine);
        toolbar = findViewById(R.id.toolbarMovie);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        MovieApi movieApi = RetrofitInstance.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovieDetails(id, ApiData.API_KEY);
        responseCall.enqueue(new Callback<MovieModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful()) {
                    movieModel = response.body();
                    recyclerView.setAdapter(new GenreAdapter(getApplicationContext(), movieModel.getGenres()));
                    if (movieModel.getBackdropPath() != null)
                        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500" + movieModel.getBackdropPath()).into(backdrop);
                    else
                        Glide.with(getApplicationContext()).load("").into(backdrop);

                    rating.setText("" + movieModel.getVoteAverage());
                    releaseDate.setText("" + movieModel.getReleaseDate());
                    budget.setText("" + (movieModel.getBudget() / 1000000) + "M");
                    revenue.setText("" + (movieModel.getRevenue() / 1000000 + "M"));
                    runtime.setText("" + (movieModel.getRuntime() / 60) + "h" + (movieModel.getRuntime() % 60) + "m");
                    tagLine.setText(movieModel.getTagline());

                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });


        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount(),id);
        viewPager.setAdapter(tabLayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

}