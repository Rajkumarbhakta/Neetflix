package com.rkbapps.neetflix.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rkbapps.neetflix.R;


public class SeriesOverviewFragment extends Fragment {

    public SeriesOverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_series_overview, container, false);
        int id = getArguments().getInt("id");

        return view;
    }
}