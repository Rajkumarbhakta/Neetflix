package com.rkbapps.neetflix.adapter.tab;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rkbapps.neetflix.fragments.SearchMovieResultFragment;
import com.rkbapps.neetflix.fragments.SearchSeriesResultFragment;

public class TabAdapterSearch extends FragmentPagerAdapter {
    private final Context context;
    private final int totalTabs;
    private String query;

    public TabAdapterSearch(@NonNull FragmentManager fm, Context context, int totalTabs, String query) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
        this.query = query;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("query",query);
        switch (position){
            case 0:
                SearchMovieResultFragment searchMovieResultFragment = new SearchMovieResultFragment();
                searchMovieResultFragment.setArguments(bundle);
                return searchMovieResultFragment;
            case 1:
                SearchSeriesResultFragment searchSeriesResultFragment = new SearchSeriesResultFragment();
                searchSeriesResultFragment.setArguments(bundle);
                return searchSeriesResultFragment;
            default: return null;
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
            case 0:return "Movies";
            case 1:return "Tv Series";
            default:return null;
        }
    }
}
