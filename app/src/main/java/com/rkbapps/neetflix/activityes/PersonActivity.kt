package com.rkbapps.neetflix.activityes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.adapter.tab.TabLayoutPersonAdapter
import com.rkbapps.neetflix.databinding.ActivityPersonBinding

class PersonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_person)

        val id = intent.getIntExtra("id", -1)
        val gender = intent.getIntExtra("gender", -1)
        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        val department = intent.getStringExtra("department")
        val popularity = intent.getDoubleExtra("popularity", 0.0)

        val personImage = binding.imgPerson
        val genderText = binding.txtGender
        val tabLayout = binding.tabLayoutPerson
        val viewPager = binding.pagerPerson

        setSupportActionBar(binding.toolbarPerson)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        when (gender) {
            1 -> {
                genderText.text = "Female"
            }

            2 -> {
                genderText.text = "Male"
            }

            else -> {
                genderText.text = "-"
            }
        }

        binding.txtKnownForDepartment.text = department
        binding.txtPopularity.text = popularity.toString() + ""

        if (image != null) {
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/$image")
                .into(personImage)
        } else {

            when (gender) {
                1 -> {
                    Glide.with(this).load(R.drawable.female_placeholder).into(personImage)
                }

                2 -> {
                    Glide.with(this).load(R.drawable.male_placeholder).into(personImage)
                }

                else -> {
                    Glide.with(this).load(R.drawable.male_placeholder).into(personImage)
                }
            }
        }
        binding.txtPersonName.text = name

        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        viewPager.adapter = TabLayoutPersonAdapter(
            supportFragmentManager,
            id,
            tabLayout.tabCount,
            applicationContext
        )
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}