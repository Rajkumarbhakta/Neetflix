package com.rkbapps.neetflix.activityes

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
import com.rkbapps.neetflix.databinding.ActivityMoviePreviewBinding
import com.rkbapps.neetflix.db.DatabaseReference.getDatabase
import com.rkbapps.neetflix.db.EntityModel
import com.rkbapps.neetflix.models.Genre
import com.rkbapps.neetflix.models.movies.MovieModel
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.ContentType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePreviewActivity : AppCompatActivity() {
    private val genreList: List<Genre> = ArrayList()
    private lateinit var movieModel: MovieModel
    private lateinit var binding: ActivityMoviePreviewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_preview)

        val id = intent.getIntExtra("id", -1)
        val tittle = intent.getStringExtra("tittle")
        val tabLayout = binding.tabLayout
        val viewPager = binding.pager

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
            if (getDatabase(this).contentDao.isBookmarked(id)) {
                getDatabase(this).contentDao.removeBookmark(id)
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
                    Toast.makeText(
                        this@MoviePreviewActivity,
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    private fun loadMovieData(id: Int) {
        RetrofitInstance.getMovieApi().getMovieDetails(id, ApiData.API_KEY)
            .enqueue(object : Callback<MovieModel?> {
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
        getDatabase(this).contentDao.addToBookmark(entityModel)
        binding.bookmarkMovie.setImageResource(R.drawable.bookmark)
        Toast.makeText(
            this@MoviePreviewActivity,
            "Added to bookmark",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }


}


//                    recyclerView.setAdapter(GenreAdapter(applicationContext, movieModel!!.genres))
//                    if (movieModel!!.backdropPath != null) Glide.with(applicationContext)
//                        .load("https://image.tmdb.org/t/p/w500" + movieModel!!.backdropPath)
//                        .into(backdrop) else Glide.with(
//                        applicationContext
//                    ).load(R.drawable.general_backdrop).into(backdrop)
//                    rating.setText("" + movieModel!!.voteAverage)
//                    releaseDate.setText("" + movieModel!!.releaseDate)
//                    budget.setText("" + movieModel!!.budget / 1000000 + "M")
//                    revenue.setText("" + ((movieModel!!.revenue / 1000000).toString() + "M"))
//                    runtime.setText("" + movieModel!!.runtime / 60 + "h" + movieModel!!.runtime % 60 + "m")
//                    tagLine.setText(movieModel!!.tagline)




