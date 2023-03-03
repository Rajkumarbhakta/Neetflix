package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.activityes.MainActivity;


public class DiscoverFragment extends Fragment {

    private TextView movies,tvSeries;


    public DiscoverFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        movies = view.findViewById(R.id.navMovies);
        tvSeries = view.findViewById(R.id.navTvSeries);
        int containerFragment = R.id.containerFrag;

        loadFragment(new MoviesFragment(),containerFragment);


        movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movies.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.selected_type_background));
                tvSeries.setBackground(null);
                loadFragment(new MoviesFragment(),containerFragment);
            }
        });

        tvSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSeries.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.selected_type_background));
                movies.setBackground(null);
                loadFragment(new TvSeriesFragment(),containerFragment);
            }
        });


        return view;
    }

    public void loadFragment(Fragment fragment, int container){
        getChildFragmentManager().beginTransaction().replace(container, fragment).commit();
    }

}