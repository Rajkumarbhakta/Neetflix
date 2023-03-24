package com.rkbapps.neetflix.activityes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.models.MovieList;

import java.util.Objects;

public class SeeMoreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
   private MaterialToolbar toolbar;
    String type="";
    int contentType = -1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);
        recyclerView = findViewById(R.id.recyclerSeeMore);
        toolbar = findViewById(R.id.toolBarSeeMore);

        type = getIntent().getStringExtra("Type");

        contentType = getIntent().getIntExtra("contentType",-1);



        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


            if(contentType== MovieList.MOVIE){
                getSupportActionBar().setTitle(type+" Movies");
            }
            if(contentType == MovieList.TV_SERIES){
                getSupportActionBar().setTitle(type+" Series");
            }






    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
}