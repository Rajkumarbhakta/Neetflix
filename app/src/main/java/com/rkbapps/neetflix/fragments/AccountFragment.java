package com.rkbapps.neetflix.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.activityes.BookmarkActivity;
import com.rkbapps.neetflix.db.SharedPreferanceValues;

import java.io.File;


public class AccountFragment extends Fragment {

    private LinearLayout bookmark, clearCache;
    private SwitchMaterial switchNsfw;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (deleteDir(dir)) {
                Toast.makeText(context, "Cache deleted.", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        bookmark = view.findViewById(R.id.linerLayoutMyBookmark);
        switchNsfw = view.findViewById(R.id.switchNsfw);
        clearCache = view.findViewById(R.id.linerLayoutClearCache);

        boolean isNsfw = SharedPreferanceValues.INSTANCE.readNsfw(requireContext());


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

                SharedPreferanceValues.INSTANCE.writeNsfw(requireContext(), isChecked);

            }
        });


        clearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCache(getContext());
            }
        });


        return view;
    }
}