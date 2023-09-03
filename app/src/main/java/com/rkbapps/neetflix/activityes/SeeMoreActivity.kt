package com.rkbapps.neetflix.activityes

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.adapter.SeeMoreItemMovieAdapter
import com.rkbapps.neetflix.adapter.TvSeriesListChildAdapter
import com.rkbapps.neetflix.databinding.ActivitySeeMoreBinding
import com.rkbapps.neetflix.models.MovieList
import com.rkbapps.neetflix.models.movies.MovieListModel
import com.rkbapps.neetflix.models.movies.MovieResult
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel
import com.rkbapps.neetflix.models.tvseries.TvSeriesResult
import com.rkbapps.neetflix.repository.SeeMoreRepository
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import com.rkbapps.neetflix.viewmodelfactories.SeeMoreViewModelFactory
import com.rkbapps.neetflix.viewmodels.SeeMoreViewModel

class SeeMoreActivity : AppCompatActivity() {

    private val tvSeriesResults: MutableList<TvSeriesResult> = ArrayList()
    private lateinit var binding: ActivitySeeMoreBinding
    private lateinit var viewModel: SeeMoreViewModel
    private lateinit var movieAdapter: SeeMoreItemMovieAdapter
    private var totalPages = 1


    private var tvSeriesList: TvSeriesListModel? = TvSeriesListModel()

    private lateinit var tvSeriesAdapter: TvSeriesListChildAdapter
    private var page = 1
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_see_more)
        val movieApi = RetrofitInstance.movieApi
        val repository = SeeMoreRepository(movieApi!!)
        viewModel = ViewModelProvider(
            this,
            SeeMoreViewModelFactory(repository)
        )[SeeMoreViewModel::class.java]


        val type = intent.getStringExtra("Type")
        val contentType = intent.getIntExtra("contentType", -1)
        setSupportActionBar(binding.toolBarSeeMore)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //movieAdapter = MovieListChildAdapter(movieResults, this)
        movieAdapter = SeeMoreItemMovieAdapter()
        tvSeriesAdapter = TvSeriesListChildAdapter(tvSeriesResults, this)
        val recyclerView = binding.recyclerSeeMore
        recyclerView.layoutManager = GridLayoutManager(this, 3)


        if (contentType == MovieList.MOVIE) {
            supportActionBar!!.title = "$type Movies"
            recyclerView.adapter = movieAdapter
            loadMovies(type, page)
            updateMovieData()
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager?
                    if ((gridLayoutManager != null) && (gridLayoutManager.findLastCompletelyVisibleItemPosition() == (movieAdapter.itemCount -1))) {
                        //bottom of list!
                        Log.d("Check function call", "onScrolled: Called")
                        if (!isLoading) {
                            page++
                            Log.d("Check function call", "onScrolled: Called 2")
                            if (totalPages >= page) {
                                Log.d("Check function call", "onScrolled: Called 3")
                                loadMovies(type, page)
                            }
                        }
                    }
                }
            })
        }
//        if (contentType == MovieList.TV_SERIES) {
//            supportActionBar!!.setTitle("$type Series")
//            recyclerView.setAdapter(tvSeriesAdapter)
//            loadSeries(type, page)
//            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager?
//                    if (gridLayoutManager != null && gridLayoutManager.findLastCompletelyVisibleItemPosition() == tvSeriesResults.size - 1) {
//                        //bottom of list!
//                        if (!isLoading) {
//                            page++
//                            if (tvSeriesList!!.totalPages >= page) {
//                                loadSeries(type, page)
//                            }
//                        }
//                    }
//                }
//            })
//        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
    private fun updateMovieData() {
        isLoading = true
        viewModel.movieList.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    //isLoading = true
                }
                is Resource.Success -> {
                        if (it.data != null) {
                            totalPages = it.data.totalPages
                            isLoading = false
                        }
                }
                is Resource.Error -> {
                    isLoading = false
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeMovieData(){
        viewModel.movieListLiveData.observe(this){
            val tempList = ArrayList<MovieResult>()
            tempList.addAll(it)
            movieAdapter.submitList(tempList.toList())
            isLoading = false
        }
    }

    private fun loadMovies(type: String?, page: Int) {
        isLoading = true
        Log.d("Check function call", "loadMovies: Called")
        if (type == "Popular") {
            viewModel.loadPopularMovies(page)
        }
        if (type == "Trending") {
            viewModel.loadTrendingMovies(page)
        }
        if (type == "Top Rated") {
            viewModel.loadTopRatedMovies(page)
        }
        if (type == "Up Coming") {
            viewModel.loadUpcomingMovies(page)
        }
        observeMovieData()
    }

//    private fun loadPopularSeries(page: Int) {
//        isLoading = true
//        tvSeriesApi!!.getPopularSeries(ApiData.API_KEY, page)
//            .enqueue(object : Callback<TvSeriesListModel> {
//                override fun onResponse(
//                    call: Call<TvSeriesListModel>,
//                    response: Response<TvSeriesListModel>
//                ) {
//                    if (response.isSuccessful) {
//                        tvSeriesList = response.body()
//                        tvSeriesResults.addAll(response.body()!!.results)
//                        tvSeriesAdapter!!.notifyDataSetChanged()
//                        isLoading = false
//                    } else {
//                        Toast.makeText(this@SeeMoreActivity, response.message(), Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//
//                override fun onFailure(call: Call<TvSeriesListModel>, t: Throwable) {
//                    Toast.makeText(this@SeeMoreActivity, t.message, Toast.LENGTH_SHORT).show()
//                }
//            })
//    }
//
//    private fun loadArrivingTodaySeries(page: Int) {
//        isLoading = true
//        tvSeriesApi!!.getAiringTodaySeries(ApiData.API_KEY, page)
//            .enqueue(object : Callback<TvSeriesListModel> {
//                override fun onResponse(
//                    call: Call<TvSeriesListModel>,
//                    response: Response<TvSeriesListModel>
//                ) {
//                    if (response.isSuccessful) {
//                        tvSeriesList = response.body()
//                        tvSeriesResults.addAll(response.body()!!.results)
//                        tvSeriesAdapter!!.notifyDataSetChanged()
//                        isLoading = false
//                    } else {
//                        Toast.makeText(this@SeeMoreActivity, response.message(), Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//
//                override fun onFailure(call: Call<TvSeriesListModel>, t: Throwable) {
//                    Toast.makeText(this@SeeMoreActivity, t.message, Toast.LENGTH_SHORT).show()
//                }
//            })
//    }
//
//    private fun loadTopRatedSeries(page: Int) {
//        isLoading = true
//        tvSeriesApi!!.getTopRatedSeries(ApiData.API_KEY, page)
//            .enqueue(object : Callback<TvSeriesListModel> {
//                override fun onResponse(
//                    call: Call<TvSeriesListModel>,
//                    response: Response<TvSeriesListModel>
//                ) {
//                    if (response.isSuccessful) {
//                        tvSeriesList = response.body()
//                        tvSeriesResults.addAll(response.body()!!.results)
//                        tvSeriesAdapter!!.notifyDataSetChanged()
//                        isLoading = false
//                    } else {
//                        Toast.makeText(this@SeeMoreActivity, response.message(), Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//
//                override fun onFailure(call: Call<TvSeriesListModel>, t: Throwable) {
//                    Toast.makeText(this@SeeMoreActivity, t.message, Toast.LENGTH_SHORT).show()
//                }
//            })
//    }
//
//    private fun loadTrendingSeries(page: Int) {
//        isLoading = true
//        tvSeriesApi!!.getTrendingSeries(ApiData.API_KEY, page)
//            .enqueue(object : Callback<TvSeriesListModel> {
//                override fun onResponse(
//                    call: Call<TvSeriesListModel>,
//                    response: Response<TvSeriesListModel>
//                ) {
//                    if (response.isSuccessful) {
//                        tvSeriesList = response.body()
//                        tvSeriesResults.addAll(response.body()!!.results)
//                        tvSeriesAdapter!!.notifyDataSetChanged()
//                        isLoading = false
//                    } else {
//                        Toast.makeText(this@SeeMoreActivity, response.message(), Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//
//                override fun onFailure(call: Call<TvSeriesListModel>, t: Throwable) {
//                    Toast.makeText(this@SeeMoreActivity, t.message, Toast.LENGTH_SHORT).show()
//                }
//            })
//    }
//
//    private fun loadSeries(type: String?, page: Int) {
//        if (type == "Popular") loadPopularSeries(page)
//        if (type == "Trending") loadTrendingSeries(page)
//        if (type == "Top Rated") loadTopRatedSeries(page)
//        if (type == "Airing Today") loadArrivingTodaySeries(page)
//    }
}