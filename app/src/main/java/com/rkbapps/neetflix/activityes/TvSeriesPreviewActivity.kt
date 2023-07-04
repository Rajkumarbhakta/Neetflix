package com.rkbapps.neetflix.activityes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.adapter.GenreAdapter
import com.rkbapps.neetflix.adapter.tab.TabLayoutAdapter
import com.rkbapps.neetflix.databinding.ActivityTvSeriesPreviewBinding
import com.rkbapps.neetflix.db.Database
import com.rkbapps.neetflix.db.DatabaseReference.getDatabase
import com.rkbapps.neetflix.db.EntityModel
import com.rkbapps.neetflix.models.tvseries.TvSeriesModel
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.ContentType
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvSeriesPreviewActivity : AppCompatActivity() {

    private lateinit var tvSeriesModel: TvSeriesModel
    private lateinit var binding: ActivityTvSeriesPreviewBinding
    private lateinit var mDatabase: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_series_preview)
        mDatabase = getDatabase(this)

        val id = intent.getIntExtra("id", -1)
        val tittle = intent.getStringExtra("tittle")

        val viewPager = binding.pager
        val tabLayout = binding.tabLayout

        binding.recyclerGenre.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        binding.bookmarkSeries.isEnabled = false


        if (mDatabase.contentDao.isBookmarked(id)) {
            binding.bookmarkSeries.setImageResource(R.drawable.bookmark)
        } else {
            binding.bookmarkSeries.setImageResource(R.drawable.bookmark_border)
        }


        setSupportActionBar(binding.toolbarTvSeries)
        supportActionBar!!.title = tittle
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        loadTvSeriesData(id)

        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        val tabAdapter = TabLayoutAdapter(
            supportFragmentManager,
            applicationContext,
            tabLayout.tabCount,
            id,
            TabLayoutAdapter.SERIES
        )
        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)

        binding.bookmarkSeries.setOnClickListener {
            if (mDatabase.contentDao.isBookmarked(id)) {
                MainScope().launch {
                    mDatabase.contentDao.removeBookmark(id)
                }
                Toast.makeText(
                    this@TvSeriesPreviewActivity,
                    "Removed from bookmark",
                    Toast.LENGTH_SHORT
                ).show()
                binding.bookmarkSeries.setImageResource(R.drawable.bookmark_border)
            } else {
                try {
                    addToBookmark(tvSeriesModel)
                } catch (e: Exception) {
                    Toast.makeText(
                        this@TvSeriesPreviewActivity,
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }
    }

    private fun addToBookmark(tvSeriesModel: TvSeriesModel) {
        val entityModel = EntityModel(
            type = ContentType.SERIES,
            contentId = 0,
            adult = tvSeriesModel.adult,
            id = tvSeriesModel.id,
            releaseDate = tvSeriesModel.firstAirDate,
            posterPath = tvSeriesModel.posterPath,
            title = tvSeriesModel.name,
            voteAverage = tvSeriesModel.voteAverage
        )
        MainScope().launch {
            mDatabase.contentDao.addToBookmark(entityModel)
        }
        binding.bookmarkSeries.setImageResource(R.drawable.bookmark)
        Toast.makeText(
            this@TvSeriesPreviewActivity,
            "Added to bookmark",
            Toast.LENGTH_SHORT
        ).show()

    }


    private fun loadTvSeriesData(id: Int) {

        RetrofitInstance.getTvSeriesApi().getSeriesDetails(id, ApiData.API_KEY)
            .enqueue(object : Callback<TvSeriesModel?> {
                override fun onResponse(
                    call: Call<TvSeriesModel?>,
                    response: Response<TvSeriesModel?>
                ) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            tvSeriesModel = response.body()!!
                            binding.recyclerGenre.adapter =
                                GenreAdapter(this@TvSeriesPreviewActivity, tvSeriesModel.genres)

                            binding.tvSeriesModel = tvSeriesModel

                            binding.bookmarkSeries.isEnabled = true
                        }
                    }
                }

                override fun onFailure(call: Call<TvSeriesModel?>, t: Throwable) {
                    Toast.makeText(
                        this@TvSeriesPreviewActivity,
                        t.localizedMessage!!.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}