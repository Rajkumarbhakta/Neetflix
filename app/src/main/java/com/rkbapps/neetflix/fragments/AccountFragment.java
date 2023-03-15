package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.activityes.BookmarkActivity;


public class AccountFragment extends Fragment {

    private Button bookmark;

    public AccountFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        bookmark = view.findViewById(R.id.btnToBookmark);

        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), BookmarkActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}