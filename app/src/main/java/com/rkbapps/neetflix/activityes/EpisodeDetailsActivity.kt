package com.rkbapps.neetflix.activityes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.adapter.credit.CastAdapter
import com.rkbapps.neetflix.databinding.ActivityEpisodeDetailsBinding
import com.rkbapps.neetflix.models.tvseries.seasons.EpisodeDetails

class EpisodeDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEpisodeDetailsBinding


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_episode_details)

        val episodePoster = binding.imgEpisodeBackdrop
        val episodeNumber = binding.txtEpisodeNumberDetails
        val episodeName = binding.txtEpisodeTittle
        val overview = binding.txtEpisodeOverview
        val recyclerGuest = binding.recyclerEpisodeGuestStars
        val guestStar = binding.txtGuestStar


        guestStar.visibility = View.GONE
        val episodeDetails = intent.getSerializableExtra("episodeDetails") as EpisodeDetails?
        setSupportActionBar(binding.toolbarEpisodePreview)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        recyclerGuest.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        if (episodeDetails != null) {
            if (episodeDetails.stillPath == null) {
                Glide.with(this).load(R.drawable.general_backdrop).into(episodePoster)
            } else {
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/original/" + episodeDetails.stillPath)
                    .into(episodePoster)
            }
            episodeName.text = episodeDetails.name
            if (episodeDetails.overview.isEmpty()) {
                overview.text = "Not available"
            } else {
                overview.text = episodeDetails.overview
            }
            if (episodeDetails.guestStars != null && episodeDetails.guestStars.size != 0) {
                guestStar.visibility = View.VISIBLE
                recyclerGuest.adapter = CastAdapter(this, episodeDetails.guestStars)
            } else {
                guestStar.visibility = View.GONE
                recyclerGuest.visibility = View.GONE
            }
            episodeNumber.text =
                "Season " + episodeDetails.seasonNumber + "  Episode " + episodeDetails.episodeNumber
            supportActionBar!!.title = "Episode " + episodeDetails.episodeNumber
        } else Toast.makeText(this, "Something Went Wrong.", Toast.LENGTH_SHORT).show()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}