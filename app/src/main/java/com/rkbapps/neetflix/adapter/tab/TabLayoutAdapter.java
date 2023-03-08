package com.rkbapps.neetflix.adapter.tab;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rkbapps.neetflix.fragments.movie.MovieOverviewFragment;
import com.rkbapps.neetflix.fragments.movie.ReviewsMovieFragment;
import com.rkbapps.neetflix.fragments.movie.VideoAndImageMovieFragment;
import com.rkbapps.neetflix.fragments.series.ReviewSeriesFragment;
import com.rkbapps.neetflix.fragments.series.SeriesOverviewFragment;
import com.rkbapps.neetflix.fragments.series.VideoAndImageSeriesFragment;

public class TabLayoutAdapter extends FragmentPagerAdapter {
    public static final int MOVIE = 0;
    public static final int SERIES = 1;
    private final Context context;
    private final int totalTabs;
    private final int movieId;
    private final int tabType;

    public TabLayoutAdapter(@NonNull FragmentManager fm, Context context, int totalTabs, int movieId, int tabType) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
        this.movieId = movieId;
        this.tabType = tabType;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", movieId);
        if (tabType == MOVIE) {
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
        } else {
            switch (position) {
                case 0:
                    SeriesOverviewFragment seriesOverviewFragment = new SeriesOverviewFragment();
                    seriesOverviewFragment.setArguments(bundle);
                    return seriesOverviewFragment;
                case 1:
                    VideoAndImageSeriesFragment videoAndImageSeriesFragment = new VideoAndImageSeriesFragment();
                    videoAndImageSeriesFragment.setArguments(bundle);
                    return videoAndImageSeriesFragment;
                case 2:
                    ReviewSeriesFragment reviewSeriesFragment = new ReviewSeriesFragment();
                    reviewSeriesFragment.setArguments(bundle);
                    return reviewSeriesFragment;
                default:
                    return null;
            }
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
                return "Videos & Images";
            case 2:
                return "Reviews";
            default:
                return null;
        }

    }

}
