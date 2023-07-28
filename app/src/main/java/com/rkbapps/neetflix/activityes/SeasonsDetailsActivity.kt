package com.rkbapps.neetflix.activityes

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.adapter.series.EpisodeAdapter
import com.rkbapps.neetflix.databinding.ActivitySeasonsDetailsBinding
import com.rkbapps.neetflix.models.tvseries.seasons.SeasonsDetails
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeasonsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeasonsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_seasons_details)

        val tvID = intent.getIntExtra("tvID", -1)
        val seasonsNumber = intent.getIntExtra("seasonsNumber", -1)
        val seasonsName = intent.getStringExtra("seasonsName")


        val context: Context = this


        setSupportActionBar(binding.toolbarSeasonsPreview)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = seasonsName

        binding.recyclerEpisode.layoutManager = LinearLayoutManager(this)

        if (tvID != -1 && seasonsNumber != -1) loadSeasonsDetails(
            tvID,
            seasonsNumber,
            context
        ) else {
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadSeasonsDetails(tvId: Int, seasonsNumber: Int, context: Context) {
        val tvSeriesApi = RetrofitInstance.tvSeriesApi
        val responseCall = tvSeriesApi!!.getSeasonsDetails(tvId, seasonsNumber, ApiData.API_KEY)
        responseCall?.enqueue(object : Callback<SeasonsDetails?> {
            override fun onResponse(
                call: Call<SeasonsDetails?>,
                response: Response<SeasonsDetails?>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        binding.recyclerEpisode.adapter =
                            EpisodeAdapter(context, response.body()!!.episodes)
                    } else Toast.makeText(
                        this@SeasonsDetailsActivity,
                        "Something went wrong.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else Toast.makeText(
                    this@SeasonsDetailsActivity,
                    response.message(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<SeasonsDetails?>, t: Throwable) {
                Toast.makeText(this@SeasonsDetailsActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}