package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.activityes.BookmarkActivity;
import com.rkbapps.neetflix.db.SharedPreferanceValues;

import java.util.Objects;


public class AccountFragment extends Fragment {

    private LinearLayout bookmark;
    private SwitchMaterial switchNsfw;

    public AccountFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        bookmark = view.findViewById(R.id.linerLayoutMyBookmark);
        switchNsfw = view.findViewById(R.id.switchNsfw);

        boolean isNsfw = SharedPreferanceValues.readNsfw(requireContext());


            switchNsfw.setChecked(isNsfw);


        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), BookmarkActivity.class);
                startActivity(i);
            }
        });

        switchNsfw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferanceValues.writeNsfw(requireContext(),isChecked);

            }
        });



        return view;
    }
}