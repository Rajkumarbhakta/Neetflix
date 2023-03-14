package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;
import com.google.android.material.tabs.TabLayout;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.tab.TabAdapterSearch;

import java.util.Objects;

public class SearchFeagment extends Fragment {

    public SearchFeagment() {
        // Required empty public constructor
    }
    private SearchBar searchBar;
    private SearchView searchView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_feagment, container, false);
        searchBar=view.findViewById(R.id.search_bar);
        searchView = view.findViewById(R.id.searchView);
        tabLayout = view.findViewById(R.id.tabLayoutSearch);
        viewPager = view.findViewById(R.id.pagerSearch);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager.setAdapter(new TabAdapterSearch(getChildFragmentManager(),getContext(),tabLayout.getTabCount(),""));
        tabLayout.setupWithViewPager(viewPager);

        searchView.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                searchBar.setText(searchView.getText());
                if(!Objects.requireNonNull(searchView.getText()).toString().equals("")) {
                    SearchMovieResultFragment.bindData(searchView.getText().toString());
                    SearchSeriesResultFragment.bindData(searchView.getText().toString());
                }
                searchView.hide();
                return false;
            }
        });


        return view;
    }
}