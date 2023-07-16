package com.rkbapps.neetflix.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.adapter.ParentAdapter
import com.rkbapps.neetflix.databinding.FragmentTvSeriesBinding
import com.rkbapps.neetflix.models.MovieList
import com.rkbapps.neetflix.repository.series.SeriesFragmentRepository
import com.rkbapps.neetflix.utils.Resource
import com.rkbapps.neetflix.viewmodelfactories.series.SeriesFragmentViewModelFactory
import com.rkbapps.neetflix.viewmodels.series.SeriesFragmentViewModel

class TvSeriesFragment : Fragment() {
    private val seriesLists: MutableList<MovieList> = ArrayList()
    private lateinit var binding: FragmentTvSeriesBinding
    private lateinit var viewModel: SeriesFragmentViewModel
    private lateinit var adapter: ParentAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_series, container, false)
        val repository = SeriesFragmentRepository()
        viewModel = ViewModelProvider(
            this,
            SeriesFragmentViewModelFactory(repository)
        )[SeriesFragmentViewModel::class.java]

        //recyclerView = view.findViewById(R.id.recyclerTvSeries)

        adapter = ParentAdapter(seriesLists, context)
        binding.recyclerTvSeries.layoutManager = LinearLayoutManager(context)
        binding.recyclerTvSeries.adapter = adapter

        loadPopularSeries()
        loadTrendingSeries()
        loadArrivingTodaySeries()
        loadTopRatedSeries()


        return binding.root
    }

    private fun loadPopularSeries() {

        viewModel.popularSeries.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        seriesLists.add(
                            0,
                            MovieList(
                                MovieList.TV_SERIES,
                                "Popular",
                                it.data.body()!!.results
                            )
                        )
                        adapter.notifyItemChanged(0)
                    }
                }

                is Resource.Error -> {

                }
            }
        })

        /*
        val tvSeriesApi = RetrofitInstance.getTvSeriesApi()
        val responseCall = tvSeriesApi.getPopularSeries(ApiData.API_KEY, 1)
        responseCall!!.enqueue(object : Callback<TvSeriesListModel> {
            override fun onResponse(
                call: Call<TvSeriesListModel>,
                response: Response<TvSeriesListModel>
            ) {
                if (response.isSuccessful) {
                    Log.d("respo", response.toString() + "")
                    seriesLists.add(
                        MovieList(
                            MovieList.TV_SERIES,
                            "Popular",
                            response.body()!!.results
                        )
                    )
                    adapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<TvSeriesListModel>, t: Throwable) {
                Log.d("respo", t.toString() + "")
            }
        })
         */
    }

    private fun loadTrendingSeries() {
        viewModel.trendingSeries.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        seriesLists.add(
                            1,
                            MovieList(
                                MovieList.TV_SERIES,
                                "Trending",
                                it.data.body()!!.results
                            )
                        )
                        adapter.notifyItemChanged(1)
                    }
                }

                is Resource.Error -> {

                }
            }
        })

        /*
        val tvSeriesApi = RetrofitInstance.getTvSeriesApi()
        val responseCall = tvSeriesApi.getTrendingSeries(ApiData.API_KEY, 1)
        responseCall!!.enqueue(object : Callback<TvSeriesListModel> {
            override fun onResponse(
                call: Call<TvSeriesListModel>,
                response: Response<TvSeriesListModel>
            ) {
                if (response.isSuccessful) {
                    //Log.d("repo",response+"");
                    seriesLists.add(
                        MovieList(
                            MovieList.TV_SERIES,
                            "Trending",
                            response.body()!!.results
                        )
                    )
                    adapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<TvSeriesListModel>, t: Throwable) {
                Log.d("repo", t.toString() + "")
            }
        })

         */
    }

    private fun loadTopRatedSeries() {

        viewModel.topRatedSeries.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        seriesLists.add(
                            2,
                            MovieList(
                                MovieList.TV_SERIES,
                                "Top Rated",
                                it.data.body()!!.results
                            )
                        )
                        adapter.notifyItemChanged(2)
                    }
                }

                is Resource.Error -> {

                }
            }
        })
        /*
        val tvSeriesApi = RetrofitInstance.getTvSeriesApi()
        val responseCall = tvSeriesApi.getTopRatedSeries(ApiData.API_KEY, 1)
        responseCall!!.enqueue(object : Callback<TvSeriesListModel> {
            override fun onResponse(
                call: Call<TvSeriesListModel>,
                response: Response<TvSeriesListModel>
            ) {
                if (response.isSuccessful) {
                    //Log.d("repo",response+"");
                    seriesLists.add(
                        MovieList(
                            MovieList.TV_SERIES,
                            "Top Rated",
                            response.body()!!.results
                        )
                    )
                    adapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<TvSeriesListModel>, t: Throwable) {
                Log.d("repo", t.toString() + "")
            }
        })

         */
    }

    private fun loadArrivingTodaySeries() {

        viewModel.arrivingTodaySeries.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        seriesLists.add(
                            3,
                            MovieList(
                                MovieList.TV_SERIES,
                                "Airing Today",
                                it.data.body()!!.results
                            )
                        )
                        adapter.notifyItemChanged(3)
                    }
                }

                is Resource.Error -> {

                }
            }
        })
        /*
        val tvSeriesApi = RetrofitInstance.getTvSeriesApi()
        val responseCall = tvSeriesApi.getAiringTodaySeries(ApiData.API_KEY, 1)
        responseCall!!.enqueue(object : Callback<TvSeriesListModel> {
            override fun onResponse(
                call: Call<TvSeriesListModel>,
                response: Response<TvSeriesListModel>
            ) {
                if (response.isSuccessful) {
                    //Log.d("repo",response+"");
                    seriesLists.add(
                        MovieList(
                            MovieList.TV_SERIES,
                            "Airing Today",
                            response.body()!!.results
                        )
                    )
                    adapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<TvSeriesListModel>, t: Throwable) {
                Log.d("repo", t.toString() + "")
            }
        })

         */
    }
}