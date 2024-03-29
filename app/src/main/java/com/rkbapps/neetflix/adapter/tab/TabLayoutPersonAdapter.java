package com.rkbapps.neetflix.adapter.tab;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rkbapps.neetflix.fragments.person.PersonImageFragment;
import com.rkbapps.neetflix.fragments.person.PersonMoviesFragment;
import com.rkbapps.neetflix.fragments.person.PersonPersonalInfoFragment;
import com.rkbapps.neetflix.fragments.person.PersonSeriesFragment;

public class TabLayoutPersonAdapter extends FragmentPagerAdapter {
    private final int id;
    private final int numOfTabs;
    private final Context context;

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

        switch (position) {
            case 0:
                PersonPersonalInfoFragment personPersonalInfoFragment = new PersonPersonalInfoFragment();
                personPersonalInfoFragment.setArguments(bundle);
                return personPersonalInfoFragment;
            case 1:
                PersonImageFragment personImageFragment = new PersonImageFragment();
                personImageFragment.setArguments(bundle);
                return personImageFragment;
            case 2:
                PersonMoviesFragment personMoviesFragment = new PersonMoviesFragment();
                personMoviesFragment.setArguments(bundle);
                return personMoviesFragment;
            case 3:
                PersonSeriesFragment personSeriesFragment = new PersonSeriesFragment();
                personSeriesFragment.setArguments(bundle);
                return personSeriesFragment;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Personal Info";
            case 1:
                return "Images";
            case 2:
                return "Movies";
            case 3:
                return "Tv Series";
            default:
                return null;
        }
    }
}
