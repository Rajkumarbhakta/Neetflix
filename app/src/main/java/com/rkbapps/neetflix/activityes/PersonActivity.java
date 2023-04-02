package com.rkbapps.neetflix.activityes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.rkbapps.neetflix.R;
import com.rkbapps.neetflix.adapter.tab.TabLayoutPersonAdapter;
import com.rkbapps.neetflix.models.person.PersonDetails;
import com.rkbapps.neetflix.services.ApiData;
import com.rkbapps.neetflix.services.PersonApi;
import com.rkbapps.neetflix.services.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonActivity extends AppCompatActivity {

    private TextView personName, popularityTxt, knownForDepartment, genderText, biography;
    private ImageView personImage;
    private MaterialToolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        int id = getIntent().getIntExtra("id", -1);

        int gender = getIntent().getIntExtra("gender", -1);
        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        String department = getIntent().getStringExtra("department");
        Double popularity = getIntent().getDoubleExtra("popularity", 0.0);


        personImage = findViewById(R.id.imgPerson);
        personName = findViewById(R.id.txtPersonName);
        popularityTxt = findViewById(R.id.txtPopularity);
        knownForDepartment = findViewById(R.id.txtKnownForDepartment);
        //biography = findViewById(R.id.txtBiography);
        genderText = findViewById(R.id.txtGender);
        toolbar = findViewById(R.id.toolbarPerson);
        tabLayout = findViewById(R.id.tabLayoutPerson);
        viewPager = findViewById(R.id.pagerPerson);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (gender == 1) {
            genderText.setText("Female");
        } else if (gender == 2) {
            genderText.setText("Male");
        } else {
            genderText.setText("-");
        }

        knownForDepartment.setText(department);
        popularityTxt.setText(popularity + "");


        if (image != null)
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + image).into(personImage);
        else {
            if (gender == 1) {
                Glide.with(this).load(R.drawable.female_placeholder).into(personImage);
            } else if (gender == 2) {
                Glide.with(this).load(R.drawable.male_placeholder).into(personImage);
            } else {
                Glide.with(this).load(R.drawable.male_placeholder).into(personImage);
            }

        }


        personName.setText(name);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        viewPager.setAdapter(new TabLayoutPersonAdapter(getSupportFragmentManager(), id, tabLayout.getTabCount(), getApplicationContext()));
        tabLayout.setupWithViewPager(viewPager);



    }


}