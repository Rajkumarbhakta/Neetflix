package com.rkbapps.neetflix.activityes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.series.EpisodeAdapter;
import com.rkbapps.neetflix.models.tvseries.seasons.SeasonsDetails;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.RetrofitInstance;
import com.rkbapps.neetflix.services.TvSeriesApi;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeasonsDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerEpisode;
    private MaterialToolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_details);
        int tvID = getIntent().getIntExtra("tvID", -1);
        int seasonsNumber = getIntent().getIntExtra("seasonsNumber", -1);
        String seasonsName = getIntent().getStringExtra("seasonsName");
        recyclerEpisode = findViewById(R.id.recyclerEpisode);
        toolbar = findViewById(R.id.toolbarSeasonsPreview);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(seasonsName);

        recyclerEpisode.setLayoutManager(new LinearLayoutManager(this));
        if (tvID != -1 && seasonsNumber != -1)
            loadSeasonsDetails(tvID, seasonsNumber);
        else
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();

    }


    private void loadSeasonsDetails(int tvId, int seasonsNumber) {
        TvSeriesApi tvSeriesApi = RetrofitInstance.getTvSeriesApi();
        Call<SeasonsDetails> responseCall = tvSeriesApi.getSeasonsDetails(tvId, seasonsNumber, ApiData.API_KEY);
        responseCall.enqueue(new Callback<SeasonsDetails>() {
            @Override
            public void onResponse(Call<SeasonsDetails> call, Response<SeasonsDetails> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        recyclerEpisode.setAdapter(new EpisodeAdapter(getApplicationContext(), response.body().getEpisodes()));
                    } else
                        Toast.makeText(SeasonsDetailsActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(SeasonsDetailsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SeasonsDetails> call, Throwable t) {
                Toast.makeText(SeasonsDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}