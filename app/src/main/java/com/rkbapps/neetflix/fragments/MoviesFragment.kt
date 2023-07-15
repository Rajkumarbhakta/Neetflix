package com.rkbapps.neetflix.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.rkbapps.neetflix.databinding.FragmentMoviesBinding
import com.rkbapps.neetflix.models.MovieList
import com.rkbapps.neetflix.repository.movies.MovieFragmentRepository
import com.rkbapps.neetflix.utils.Resource
import com.rkbapps.neetflix.viewmodelfactories.movies.MovieFragmentViewModelFactory
import com.rkbapps.neetflix.viewmodels.movies.MovieFragmentViewModel

class MoviesFragment : Fragment() {
    private val movieLists: MutableList<MovieList> = ArrayList()
    private lateinit var adapter: ParentAdapter
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MovieFragmentViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        val repository = MovieFragmentRepository()

        viewModel = ViewModelProvider(
            requireActivity(),
            MovieFragmentViewModelFactory(repository)
        )[MovieFragmentViewModel::class.java]


        adapter = ParentAdapter(movieLists, context)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter


        loadPopularMovies()
        loadTrendingMovies()
        loadTopRatedMovies()
        //loadLatestMovies()
        loadUpComingMovies()

        return binding.root
    }


    private fun loadPopularMovies() {

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        movieLists.add(
                            0,
                            MovieList(
                                MovieList.MOVIE, "Popular",
                                it.data.body()!!.results, 1
                            )
                        )
                        adapter.notifyItemChanged(0)
                    }
                }

                is Resource.Error -> {

                }
            }

        })
    }

    private fun loadTrendingMovies() {
        viewModel.trendingMovies.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        movieLists.add(
                            1,
                            MovieList(
                                MovieList.MOVIE, "Trending",
                                it.data.body()!!.results, 1
                            )
                        )
                        adapter.notifyItemChanged(1)
                    }
                }

                is Resource.Error -> {

                }
            }
        })
    }


    private fun loadTopRatedMovies() {
        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        movieLists.add(
                            2,
                            MovieList(
                                MovieList.MOVIE, "Top Rated",
                                it.data.body()!!.results, 1
                            )
                        )
                        adapter.notifyItemChanged(2)
                    }
                }

                is Resource.Error -> {

                }
            }
        })
    }


    private fun loadLatestMovies() {
        viewModel.latestMovies.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {

                    Log.d("latest", "${it.data!!.body()?.results}")

                    if (it.data.body() != null) {
                        movieLists.add(
                            4,
                            MovieList(
                                MovieList.MOVIE, "Latest",
                                it.data.body()!!.results, 1
                            )
                        )
                        adapter.notifyItemChanged(4)
                    }
                }

                is Resource.Error -> {
                    Log.d("latest", it.toString())
                }
            }
        })
    }


    private fun loadUpComingMovies() {
        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        movieLists.add(
                            3,
                            MovieList(
                                MovieList.MOVIE, "Up Coming",
                                it.data.body()!!.results, 1
                            )
                        )
                        adapter.notifyItemChanged(3)
                    }
                }

                is Resource.Error -> {

                }
            }
        })
    }


//    private void loadPopularMovies() {
    //        MovieApi movieApi = RetrofitInstance.getMovieApi();
    //        Call<MovieListModel> responseCall = movieApi.getPopularMovies(ApiData.API_KEY, 1);
    //        responseCall.enqueue(new Callback<MovieListModel>() {
    //            @Override
    //            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
    //                if (response.isSuccessful()) {
    //                    movieLists.add(new MovieList(MovieList.MOVIE, "Popular", response.body().getResults(), 1));
    //                    adapter.notifyDataSetChanged();
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<MovieListModel> call, Throwable t) {
    //
    //            }
    //        });
    //    }
    //    private void loadTrendingMovies() {
    //        MovieApi movieApi = RetrofitInstance.getMovieApi();
    //        Call<MovieListModel> responseCall = movieApi.getTrendingMovies(ApiData.API_KEY, 1);
    //        responseCall.enqueue(new Callback<MovieListModel>() {
    //            @Override
    //            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
    //                if (response.isSuccessful()) {
    //                    movieLists.add(new MovieList(MovieList.MOVIE, "Trending", response.body().getResults(), 1));
    //                    adapter.notifyDataSetChanged();
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<MovieListModel> call, Throwable t) {
    //
    //            }
    //        });
    //    }
    //    private void loadTopRatedMovies() {
    //        //topRatedMovieList.clear();
    //        MovieApi movieApi = RetrofitInstance.getMovieApi();
    //        Call<MovieListModel> responseCall = movieApi.getTopRatedMovies(ApiData.API_KEY, 1);
    //        responseCall.enqueue(new Callback<MovieListModel>() {
    //            @Override
    //            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
    //                if (response.isSuccessful()) {
    ////                    topRatedMovieList = response.body().getResults();
    ////                    //adapter = new MovieListAdapter(popularMovieList,getContext());
    ////                    topRated.setAdapter(new MovieListAdapter(topRatedMovieList, getContext()));
    //
    //                    movieLists.add(new MovieList(MovieList.MOVIE, "Top Rated", response.body().getResults(), 1));
    //                    adapter.notifyDataSetChanged();
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<MovieListModel> call, Throwable t) {
    //
    //            }
    //        });
    //    }
    //    private void loadLatestMovies() {
    //        //latestMovieList.clear();
    //        MovieApi movieApi = RetrofitInstance.getMovieApi();
    //        Call<MovieListModel> responseCall = movieApi.getLatestMovies(ApiData.API_KEY, 1);
    //        responseCall.enqueue(new Callback<MovieListModel>() {
    //            @Override
    //            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
    //                if (response.isSuccessful()) {
    ////                    latestMovieList = response.body().getResults();
    ////                    //adapter = new MovieListAdapter(popularMovieList,getContext());
    ////                    latest.setAdapter(new MovieListAdapter(latestMovieList, getContext()));
    //                    movieLists.add(new MovieList(MovieList.MOVIE, "Latest", response.body().getResults(), 1));
    //                    adapter.notifyDataSetChanged();
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<MovieListModel> call, Throwable t) {
    //
    //            }
    //        });
    //    }
    //    private void loadUpComingMovies() {
    //        //upComingMovieList.clear();
    //        MovieApi movieApi = RetrofitInstance.getMovieApi();
    //        Call<MovieListModel> responseCall = movieApi.getUpcomingMovies(ApiData.API_KEY, 1);
    //        responseCall.enqueue(new Callback<MovieListModel>() {
    //            @Override
    //            public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
    //                if (response.isSuccessful()) {
    ////                    upComingMovieList = response.body().getResults();
    ////                    //adapter = new MovieListAdapter(popularMovieList,getContext());
    ////                    upComing.setAdapter(new MovieListAdapter(upComingMovieList, getContext()));
    //                    movieLists.add(new MovieList(MovieList.MOVIE, "Up Coming", response.body().getResults(), 1));
    //                    adapter.notifyDataSetChanged();
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<MovieListModel> call, Throwable t) {
    //
    //            }
    //        });
    //    }
}