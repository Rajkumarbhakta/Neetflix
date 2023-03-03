package com.rkbapps.neetflix.activityes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.fragments.AccountFragment;
import com.rkbapps.neetflix.fragments.DiscoverFragment;
import com.rkbapps.neetflix.fragments.SearchFeagment;
import com.rkbapps.neetflix.models.MovieList;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.MovieApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bNav;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bNav = findViewById(R.id.bottomNavigationView);
        int container = R.id.containerMain;


        loadFragment(new DiscoverFragment(),container);
        bNav.setSelected(true);

        bNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.discover:
                        loadFragment(new DiscoverFragment(),container);
                        return true;
                    case R.id.search:
                        loadFragment(new SearchFeagment(),container);
                        return true;
                    case R.id.account:
                        loadFragment(new AccountFragment(),container);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    public void loadFragment(Fragment fragment, int container){
        getSupportFragmentManager().beginTransaction().replace(container, fragment).commit();
    }
}