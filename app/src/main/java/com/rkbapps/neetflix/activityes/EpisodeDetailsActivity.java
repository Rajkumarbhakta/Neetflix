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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.credit.CastAdapter;
import com.rkbapps.neetflix.models.tvseries.seasons.EpisodeDetails;

import java.util.Objects;

public class EpisodeDetailsActivity extends AppCompatActivity {
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_details);

        ImageView episodePoster = findViewById(R.id.imgEpisodeBackdrop);
        TextView episodeNumber = findViewById(R.id.txtEpisodeNumberDetails);
        TextView episodeName = findViewById(R.id.txtEpisodeTittle);
        TextView overview = findViewById(R.id.txtEpisodeOverview);
        RecyclerView recyclerGuest = findViewById(R.id.recyclerEpisodeGuestStars);
        MaterialToolbar toolbar = findViewById(R.id.toolbarEpisodePreview);
        TextView guestStar = findViewById(R.id.txtGuestStar);

        guestStar.setVisibility(View.GONE);

        EpisodeDetails episodeDetails = (EpisodeDetails) getIntent().getSerializableExtra("episodeDetails");

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerGuest.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        if (episodeDetails != null) {
            if (episodeDetails.getStillPath() == null) {
                Glide.with(this).load(R.drawable.general_backdrop).into(episodePoster);
            } else {
                Glide.with(this).load("https://image.tmdb.org/t/p/original/" + episodeDetails.getStillPath()).into(episodePoster);
            }

            episodeName.setText(episodeDetails.getName());

            if(episodeDetails.getOverview().isEmpty()){
                overview.setText("Not available");
            }else {
                overview.setText(episodeDetails.getOverview());
            }


            if(episodeDetails.getGuestStars()!=null && episodeDetails.getGuestStars().size()!=0){
                guestStar.setVisibility(View.VISIBLE);
                recyclerGuest.setAdapter(new CastAdapter(this, episodeDetails.getGuestStars()));
            }else{
                guestStar.setVisibility(View.GONE);
                recyclerGuest.setVisibility(View.GONE);
            }


            episodeNumber.setText("Season " + episodeDetails.getSeasonNumber() + "  Episode " + episodeDetails.getEpisodeNumber());

            getSupportActionBar().setTitle("Episode " + episodeDetails.getEpisodeNumber());

        } else
            Toast.makeText(this, "Something Went Wrong.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}