package com.rkbapps.neetflix.activityes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.MovieListChildAdapter;
import com.rkbapps.neetflix.adapter.TvSeriesListChildAdapter;
import com.rkbapps.neetflix.models.MovieList;
import com.rkbapps.neetflix.models.movies.MovieListModel;
import com.rkbapps.neetflix.models.movies.MovieResult;
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel;
import com.rkbapps.neetflix.models.tvseries.TvSeriesResult;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.MovieApi;
import com.rkbapps.neetflix.services.RetrofitInstance;
import com.rkbapps.neetflix.services.TvSeriesApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeMoreActivity extends AppCompatActivity {

    private final MovieApi movieApi = RetrofitInstance.getMovieApi();
    private final TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();
    private final List<MovieResult> movieResults = new ArrayList<>();
    private final List<TvSeriesResult> tvSeriesResults = new ArrayList<>();
    String type = "";
    int contentType = -1;
    private RecyclerView recyclerView;
    private MaterialToolbar toolbar;
    private MovieListModel movieList = new MovieListModel();
    private MovieListChildAdapter movieAdapter;
    private TvSeriesListModel tvSeriesList = new TvSeriesListModel();
    private TvSeriesListChildAdapter tvSeriesAdapter;


    private int page = 1;

    private boolean isLoading = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);
        recyclerView = findViewById(R.id.recyclerSeeMore);
        toolbar = findViewById(R.id.toolBarSeeMore);

        type = getIntent().getStringExtra("Type");

        contentType = getIntent().getIntExtra("contentType", -1);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        movieAdapter = new MovieListChildAdapter(movieResults, this);
        tvSeriesAdapter = new TvSeriesListChildAdapter(tvSeriesResults, this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));


        if (contentType == MovieList.MOVIE) {
            getSupportActionBar().setTitle(type + " Movies");
            recyclerView.setAdapter(movieAdapter);
            loadMovies(type, page);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

                    if (gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() == movieResults.size() - 1) {
                        //bottom of list!
                        if (!isLoading) {
                            page++;
                            if (movieList.getTotalPages() >= page) {
                                loadMovies(type, page);
                            }
                        }
                    }

                }
            });
        }

        if (contentType == MovieList.TV_SERIES) {
            getSupportActionBar().setTitle(type + " Series");
            recyclerView.setAdapter(tvSeriesAdapter);
            loadSeries(type, page);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

                    if (gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() == tvSeriesResults.size() - 1) {
                        //bottom of list!
                        if (!isLoading) {
                            page++;
                            if (tvSeriesList.getTotalPages() >= page) {
                                loadSeries(type, page);
                            }
                        }
                    }

                }
            });

        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }

    private void loadPopularMovies(int Page) {
        isLoading = true;
        movieApi.getPopularMovies(ApiData.API_KEY, page).enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
                    movieList = response.body();
                    movieResults.addAll(response.body().getResults());
                    movieAdapter.notifyDataSetChanged();
                    isLoading = false;
                } else
                    Toast.makeText(SeeMoreActivity.this, response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {
                Toast.makeText(SeeMoreActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadTrendingMovies(int page) {
        isLoading = true;
        movieApi.getTrendingMovies(ApiData.API_KEY, page)
                .enqueue(new Callback<MovieListModel>() {
                    @Override
                    public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                        if (response.isSuccessful()) {
                            movieList = response.body();
                            movieResults.addAll(response.body().getResults());
                            movieAdapter.notifyDataSetChanged();
                            isLoading = false;
                        } else
                            Toast.makeText(SeeMoreActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<MovieListModel> call, Throwable t) {
                        Toast.makeText(SeeMoreActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadTopRatedMovies(int page) {
        isLoading = true;
        movieApi.getTopRatedMovies(ApiData.API_KEY, page).enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
                    movieList = response.body();
                    movieResults.addAll(response.body().getResults());
                    movieAdapter.notifyDataSetChanged();
                    isLoading = false;
                } else
                    Toast.makeText(SeeMoreActivity.this, response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {
                Toast.makeText(SeeMoreActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUpComingMovies(int page) {
        isLoading = true;
        movieApi.getUpcomingMovies(ApiData.API_KEY, page).enqueue(new Callback<MovieListModel>() {
            @Override
            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                if (response.isSuccessful()) {
                    movieList = response.body();
                    movieResults.addAll(response.body().getResults());
                    movieAdapter.notifyDataSetChanged();
                    isLoading = false;
                } else
                    Toast.makeText(SeeMoreActivity.this, response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MovieListModel> call, Throwable t) {
                Toast.makeText(SeeMoreActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadMovies(String type, int page) {

        if (Objects.equals(type, "Popular")) {
            loadPopularMovies(page);
        }
        if (Objects.equals(type, "Trending")) {
            loadTrendingMovies(page);
        }
        if (Objects.equals(type, "Top Rated")) {
            loadTopRatedMovies(page);
        }
        if (Objects.equals(type, "Up Coming")) {
            loadUpComingMovies(page);
        }

    }


    private void loadPopularSeries(int page) {
        isLoading = true;
        tvSeriesApi.getPopularSeries(ApiData.API_KEY, page).enqueue(new Callback<TvSeriesListModel>() {
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {

                if (response.isSuccessful()) {
                    tvSeriesList = response.body();
                    tvSeriesResults.addAll(response.body().getResults());
                    tvSeriesAdapter.notifyDataSetChanged();
                    isLoading = false;
                } else {
                    Toast.makeText(SeeMoreActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {
                Toast.makeText(SeeMoreActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void loadArrivingTodaySeries(int page) {
        isLoading = true;
        tvSeriesApi.getAiringTodaySeries(ApiData.API_KEY, page).enqueue(new Callback<TvSeriesListModel>() {
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {

                if (response.isSuccessful()) {
                    tvSeriesList = response.body();
                    tvSeriesResults.addAll(response.body().getResults());
                    tvSeriesAdapter.notifyDataSetChanged();
                    isLoading = false;
                } else {
                    Toast.makeText(SeeMoreActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {
                Toast.makeText(SeeMoreActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTopRatedSeries(int page) {
        isLoading = true;
        tvSeriesApi.getTopRatedSeries(ApiData.API_KEY, page).enqueue(new Callback<TvSeriesListModel>() {
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {
                if (response.isSuccessful()) {
                    tvSeriesList = response.body();
                    tvSeriesResults.addAll(response.body().getResults());
                    tvSeriesAdapter.notifyDataSetChanged();
                    isLoading = false;
                } else {
                    Toast.makeText(SeeMoreActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {
                Toast.makeText(SeeMoreActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadTrendingSeries(int page) {
        isLoading = true;
        tvSeriesApi.getTrendingSeries(ApiData.API_KEY, page).enqueue(new Callback<TvSeriesListModel>() {
            @Override
            public void onResponse(Call<TvSeriesListModel> call, Response<TvSeriesListModel> response) {

                if (response.isSuccessful()) {
                    tvSeriesList = response.body();
                    tvSeriesResults.addAll(response.body().getResults());
                    tvSeriesAdapter.notifyDataSetChanged();
                    isLoading = false;
                } else {
                    Toast.makeText(SeeMoreActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TvSeriesListModel> call, Throwable t) {
                Toast.makeText(SeeMoreActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadSeries(String type, int page) {
        if (Objects.equals(type, "Popular"))
            loadPopularSeries(page);
        if (Objects.equals(type, "Trending"))
            loadTrendingSeries(page);
        if (Objects.equals(type, "Top Rated"))
            loadTopRatedSeries(page);
        if (Objects.equals(type, "Airing Today"))
            loadArrivingTodaySeries(page);

    }

}