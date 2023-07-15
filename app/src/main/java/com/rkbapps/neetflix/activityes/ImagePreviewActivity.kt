package com.rkbapps.neetflix.activityes

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.databinding.ActivityImagePreviewBinding

class ImagePreviewActivity : AppCompatActivity() {

    lateinit var binding: ActivityImagePreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_preview)

        setSupportActionBar(binding.toolbarImagePreview)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""

        val imageKey = intent.getStringExtra("imageKey")
        if (imageKey != null) {
            Glide.with(this).load("https://image.tmdb.org/t/p/original/$imageKey")
                .into(binding.imgPreview)
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}