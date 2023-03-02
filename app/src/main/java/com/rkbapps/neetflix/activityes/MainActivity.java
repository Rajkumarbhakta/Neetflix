package com.rkbapps.neetflix.activityes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.models.MovieList;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.MovieApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieApi movieApi = RetrofitInstance.getMovieApi();

                Call<MovieList> response = movieApi.getPopularMovies(ApiData.API_KEY);

                response.enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        if(response.isSuccessful()){
                            Log.d("Tag",response.body().toString() + response.code());
                        }else{
                            Log.d("Tag",""+response);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {

                    }
                });

            }
        });
    }
}