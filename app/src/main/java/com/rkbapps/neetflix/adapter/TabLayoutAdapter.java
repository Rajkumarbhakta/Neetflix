package com.rkbapps.neetflix.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rkbapps.neetflix.fragments.MovieOverviewFragment;
import com.rkbapps.neetflix.fragments.ReviewsMovieFragment;
import com.rkbapps.neetflix.fragments.VideoAndImageMovieFragment;

public class TabLayoutAdapter extends FragmentPagerAdapter {

    private final Context context;
    private final int totalTabs;
    private final int movieId;

    public TabLayoutAdapter(@NonNull FragmentManager fm, Context context, int totalTabs, int movieId) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
        this.movieId = movieId;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", movieId);
        switch (position) {
            case 0:
                MovieOverviewFragment movieOverviewFragment = new MovieOverviewFragment();
                movieOverviewFragment.setArguments(bundle);
                return movieOverviewFragment;
            case 1:
                VideoAndImageMovieFragment videoAndImageMovieFragment = new VideoAndImageMovieFragment();
                videoAndImageMovieFragment.setArguments(bundle);
                return videoAndImageMovieFragment;
            case 2:
                ReviewsMovieFragment reviewsMovieFRagment = new ReviewsMovieFragment();
                reviewsMovieFRagment.setArguments(bundle);
                return reviewsMovieFRagment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Overview";
            case 1:
                return "Video & Images";
            case 2:
                return "Reviews";
            default:
                return null;
        }
    }
}
