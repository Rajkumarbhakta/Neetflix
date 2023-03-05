package com.rkbapps.neetflix.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rkbapps.neetflix.R;


public class VideoAndImageMovieFragment extends Fragment {

    public VideoAndImageMovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_and_image_movie, container, false);
    }
}