package com.rkbapps.neetflix.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rkbapps.neetflix.fragments.MovieOverviewFragment;
import com.rkbapps.neetflix.fragments.ReviewsMovieFRagment;
import com.rkbapps.neetflix.fragments.VideoAndImageMovieFragment;

public class TabLayoutAdapter extends FragmentPagerAdapter {

    private Context context;
    private int totalTabs;

    public TabLayoutAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MovieOverviewFragment();
            case 1:
                return new VideoAndImageMovieFragment();
            case 2:
                return new ReviewsMovieFRagment();
            default:return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Overview";
            case 1:
                return "Video & Images";
            case 2:
                return "Reviews";
            default:return null;
        }
    }
}
