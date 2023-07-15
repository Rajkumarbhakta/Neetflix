package com.rkbapps.neetflix.activityes

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.adapter.GenreAdapter
import com.rkbapps.neetflix.adapter.tab.TabLayoutAdapter
import com.rkbapps.neetflix.databinding.ActivityMoviePreviewBinding
import com.rkbapps.neetflix.db.Database
import com.rkbapps.neetflix.db.DatabaseReference.getDatabase
import com.rkbapps.neetflix.db.EntityModel
import com.rkbapps.neetflix.models.movies.MovieModel
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.ContentType
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePreviewActivity : AppCompatActivity() {
    private lateinit var movieModel: MovieModel
    private lateinit var binding: ActivityMoviePreviewBinding
    private lateinit var mDatabase: Database


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_preview)
        mDatabase = getDatabase(this)

        val id = intent.getIntExtra("id", -1)
        val tittle = intent.getStringExtra("tittle")
        val tabLayout = binding.tabLayout
        val viewPager = binding.pager

        Log.d("database", mDatabase.toString())

        binding.bookmarkMovie.isEnabled = false

        if (getDatabase(this).contentDao.isBookmarked(id)) {
            binding.bookmarkMovie.setImageResource(R.drawable.bookmark)
        } else {
            binding.bookmarkMovie.setImageResource(R.drawable.bookmark_border)
        }



        setSupportActionBar(binding.toolbarMovie)
        supportActionBar?.title = tittle
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.recyclerGenre.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)


        loadMovieData(id)

        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        val tabLayoutAdapter = TabLayoutAdapter(
            supportFragmentManager,
            this,
            tabLayout.tabCount,
            id,
            TabLayoutAdapter.MOVIE
        )
        viewPager.adapter = tabLayoutAdapter
        tabLayout.setupWithViewPager(viewPager)


        binding.bookmarkMovie.setOnClickListener {

            if (mDatabase.contentDao.isBookmarked(id)) {
                MainScope().launch {
                    mDatabase.contentDao.removeBookmark(id)
                }
                Toast.makeText(
                    this@MoviePreviewActivity,
                    "Removed from bookmark",
                    Toast.LENGTH_SHORT
                ).show()
                binding.bookmarkMovie.setImageResource(R.drawable.bookmark_border)
            } else {
                try {
                    addToBookmark(movieModel)
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@MoviePreviewActivity,
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


    private fun loadMovieData(id: Int) {
//        val response = RetrofitInstance.getMovieApi().getMovieDetails(id, ApiData.API_KEY)
//
//        if (response.isSuccessful) {
//            if (response.body() != null) {
//                movieModel = response.body()!!
//                binding.recyclerGenre.adapter =
//                    GenreAdapter(this@MoviePreviewActivity, movieModel.genres)
//                binding.movieModel = movieModel
//                binding.bookmarkMovie.isEnabled = true
//            } else {
//                Toast.makeText(
//                    this@MoviePreviewActivity,
//                    " ${response.code()} : ${response.message()}",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        } else {
//            Toast.makeText(
//                this@MoviePreviewActivity,
//                " ${response.code()} : ${response.message()}",
//                Toast.LENGTH_SHORT
//            ).show()
//        }

        RetrofitInstance.getMovieApi().getMovieDetails(id, ApiData.API_KEY).enqueue(object :
            Callback<MovieModel?> {
            override fun onResponse(call: Call<MovieModel?>, response: Response<MovieModel?>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        movieModel = response.body()!!
                        binding.recyclerGenre.adapter =
                            GenreAdapter(this@MoviePreviewActivity, movieModel.genres)
                        binding.movieModel = movieModel
                        binding.bookmarkMovie.isEnabled = true
                    }
                }
            }

            override fun onFailure(call: Call<MovieModel?>, t: Throwable) {

                Toast.makeText(
                    this@MoviePreviewActivity,
                    t.localizedMessage!!.toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }


    private fun addToBookmark(movieModel: MovieModel) {
        val entityModel = EntityModel(
            type = ContentType.MOVIE,
            contentId = 0,
            adult = movieModel.adult,
            id = movieModel.id,
            releaseDate = movieModel.releaseDate,
            posterPath = movieModel.posterPath,
            title = movieModel.title,
            voteAverage = movieModel.voteAverage
        )
        MainScope().launch {
            mDatabase.contentDao.addToBookmark(entityModel)
        }

        binding.bookmarkMovie.setImageResource(R.drawable.bookmark)
        Toast.makeText(
            this@MoviePreviewActivity,
            "Added to bookmark",
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }


}





