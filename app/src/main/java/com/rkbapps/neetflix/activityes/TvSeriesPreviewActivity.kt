package com.rkbapps.neetflix.activityes

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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
import com.rkbapps.neetflix.repository.series.TvSeriesPreviewRepository
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.ContentType
import com.rkbapps.neetflix.viewmodelfactories.series.TvSeriesPreviewViewModelFactory
import com.rkbapps.neetflix.viewmodels.series.TvSeriesPreviewViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TvSeriesPreviewActivity : AppCompatActivity() {

    private lateinit var tvSeriesModel: TvSeriesModel
    private lateinit var binding: ActivityTvSeriesPreviewBinding
    private lateinit var viewModel: TvSeriesPreviewViewModel
    private lateinit var mDatabase: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_series_preview)
        mDatabase = getDatabase(this)
        val id = intent.getIntExtra("id", -1)
        val tittle = intent.getStringExtra("tittle")
        val repository = TvSeriesPreviewRepository(RetrofitInstance.tvSeriesApi!!)
        viewModel =
            ViewModelProvider(
                this,
                TvSeriesPreviewViewModelFactory(repository, id)
            )[TvSeriesPreviewViewModel::class.java]

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

        viewModel.seriesDetails.observe(this) {
            if (it != null) {
                tvSeriesModel = it
                binding.recyclerGenre.adapter =
                    GenreAdapter(this@TvSeriesPreviewActivity, tvSeriesModel.genres)

                binding.tvSeriesModel = tvSeriesModel

                binding.bookmarkSeries.isEnabled = true
            } else {
                Toast.makeText(
                    this@TvSeriesPreviewActivity,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

//        RetrofitInstance.tvSeriesApi!!.getSeriesDetails(id, ApiData.API_KEY)
//            ?.enqueue(object : Callback<TvSeriesModel?> {
//                override fun onResponse(
//                    call: Call<TvSeriesModel?>,
//                    response: Response<TvSeriesModel?>,
//                ) {
//                    if (response.isSuccessful) {
//                        if (response.body() != null) {
//                            tvSeriesModel = response.body()!!
//                            binding.recyclerGenre.adapter =
//                                GenreAdapter(this@TvSeriesPreviewActivity, tvSeriesModel.genres)
//
//                            binding.tvSeriesModel = tvSeriesModel
//
//                            binding.bookmarkSeries.isEnabled = true
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<TvSeriesModel?>, t: Throwable) {
//                    Toast.makeText(
//                        this@TvSeriesPreviewActivity,
//                        t.localizedMessage!!.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}