package com.rkbapps.neetflix.adapter;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rkbapps.neetflix.fragments.PersonMoviesAndSeriesFragment;
import com.rkbapps.neetflix.fragments.PersonPersonalInfoFragment;

public class TabLayoutPersonAdapter extends FragmentPagerAdapter {
    private int id;
    private int numOfTabs;
    private Context context;

    public TabLayoutPersonAdapter(@NonNull FragmentManager fm, int id, int numOfTabs, Context context) {
        super(fm);
        this.id = id;
        this.numOfTabs = numOfTabs;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt("id", id);

        switch (position){
            case 0:
                PersonPersonalInfoFragment personPersonalInfoFragment = new PersonPersonalInfoFragment();
                personPersonalInfoFragment.setArguments(bundle);
                return personPersonalInfoFragment;
            case 1:
                PersonMoviesAndSeriesFragment personMoviesAndSeriesFragment = new PersonMoviesAndSeriesFragment();
                personMoviesAndSeriesFragment.setArguments(bundle);
                return personMoviesAndSeriesFragment;

            default:return null;

        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Personal Info";
            case 1: return  "Movies & TvSeries";
            default:return null;
        }
    }
}
