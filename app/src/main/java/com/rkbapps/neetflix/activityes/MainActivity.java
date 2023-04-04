package com.rkbapps.neetflix.activityes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.fragments.AccountFragment;
import com.rkbapps.neetflix.fragments.DiscoverFragment;
import com.rkbapps.neetflix.fragments.SearchFeagment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bNav;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bNav = findViewById(R.id.bottomNavigationView);
        int container = R.id.containerMain;


        loadFragment(new DiscoverFragment(), container);
        bNav.setSelected(true);
        bNav.setSelectedItemId(R.id.discover);

        bNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.discover:
                        loadFragment(new DiscoverFragment(), container);
                        return true;
                    case R.id.search:
                        loadFragment(new SearchFeagment(), container);
                        return true;
                    case R.id.account:
                        loadFragment(new AccountFragment(), container);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    public void loadFragment(Fragment fragment, int container) {
        getSupportFragmentManager().beginTransaction().replace(container, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        int selectedItem = bNav.getSelectedItemId();
        if(selectedItem!=R.id.discover){
            loadFragment(new DiscoverFragment(), R.id.containerMain);
            bNav.setSelectedItemId(R.id.discover);
        }else {
            super.onBackPressed();
        }
    }
}