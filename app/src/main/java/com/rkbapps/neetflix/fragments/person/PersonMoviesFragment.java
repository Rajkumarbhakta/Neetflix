package com.rkbapps.neetflix.fragments.person;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rkbapps.neetflix.R;

public class PersonMoviesFragment extends Fragment {

    public PersonMoviesFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_movies, container, false);
        int id = getArguments().getInt("id");
        recyclerView = view.findViewById(R.id.recyclerPersonMovies);



        return view;
    }
}