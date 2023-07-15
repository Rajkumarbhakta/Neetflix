package com.rkbapps.neetflix.fragments.movie

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
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.adapter.MovieListChildAdapter
import com.rkbapps.neetflix.adapter.ProductionCompanyAdapter
import com.rkbapps.neetflix.adapter.credit.CastAdapter
import com.rkbapps.neetflix.adapter.credit.CrewAdapter
import com.rkbapps.neetflix.databinding.FragmentMovieOverviewBinding
import com.rkbapps.neetflix.repository.movies.MovieOverviewFragmentRepository
import com.rkbapps.neetflix.utils.Resource
import com.rkbapps.neetflix.viewmodelfactories.movies.MovieOverviewFragmentViewModelFactory
import com.rkbapps.neetflix.viewmodels.movies.MovieOverviewFragmentViewModel

class MovieOverviewFragment : Fragment() {
    private lateinit var binding: FragmentMovieOverviewBinding
    private lateinit var viewModel: MovieOverviewFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_overview, container, false)
        val id = requireArguments().getInt("id")
        val repository = MovieOverviewFragmentRepository()
        viewModel = ViewModelProvider(
            requireActivity(),
            MovieOverviewFragmentViewModelFactory(repository, id)
        )[MovieOverviewFragmentViewModel::class.java]


        binding.txtCastMovieOverview.visibility = View.GONE
        binding.txtCrewMovieOverview.visibility = View.GONE
        binding.txtProductionCompanyMovie.visibility = View.GONE
        binding.txtSimilarMovies.visibility = View.GONE

        binding.recyclerCrewMovie.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerProductionCompanyMovie.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.HORIZONTAL,
            false
        )
        binding.recyclerCastMovie.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerSimilarMovies.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)



        loadMovieDetails(id)
        loadCredits(id)
        loadSimilarMovies(id)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun loadMovieDetails(id: Int) {
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            when (it) {

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        if (it.data.body()!!.overview.isEmpty()) {
                            binding.txtMovieOverview.text = "Not Available"
                        } else {
                            binding.txtMovieOverview.text = it.data.body()!!.overview
                        }
                        if (it.data.body()!!.productionCompanies.isNotEmpty()) {
                            binding.txtProductionCompanyMovie.visibility = View.VISIBLE
                            binding.recyclerProductionCompanyMovie.adapter =
                                ProductionCompanyAdapter(
                                    context,
                                    it.data.body()!!.productionCompanies
                                )
                        } else {
                            binding.txtProductionCompanyMovie.visibility = View.GONE
                            binding.recyclerProductionCompanyMovie.visibility = View.GONE
                        }
                    }
                }

                is Resource.Error -> {

                }


            }
        })
    }

    private fun loadCredits(id: Int) {

        viewModel.creditsData.observe(viewLifecycleOwner, Observer {
            when (it) {

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        if (it.data.body()!!.cast.size != 0 && it.data.body()!!.cast != null) {
                            binding.txtCastMovieOverview.visibility = View.VISIBLE
                            binding.recyclerCastMovie.adapter =
                                CastAdapter(requireContext(), it.data.body()!!.cast)
                        } else {
                            binding.txtCastMovieOverview.visibility = View.GONE
                            binding.recyclerCastMovie.visibility = View.GONE
                        }
                        if (it.data.body()!!.crew.size != 0 && it.data.body()!!.crew != null) {
                            binding.txtCrewMovieOverview.visibility = View.VISIBLE
                            binding.recyclerCrewMovie.adapter =
                                CrewAdapter(requireContext(), it.data.body()!!.crew)
                        } else {
                            binding.txtCrewMovieOverview.visibility = View.GONE
                            binding.recyclerCrewMovie.visibility = View.GONE
                        }
                    }
                }

                is Resource.Error -> {

                }


            }
        })
    }

    private fun loadSimilarMovies(id: Int) {
        viewModel.similarMoviesData.observe(viewLifecycleOwner, Observer {
            when (it) {

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        if (it.data.body()!!.results != null && it.data.body()!!.results.isNotEmpty()) {
                            binding.txtSimilarMovies.visibility = View.VISIBLE
                            binding.recyclerSimilarMovies.adapter =
                                MovieListChildAdapter(it.data.body()!!.results, requireContext())
                        } else {
                            binding.txtSimilarMovies.visibility = View.GONE
                            binding.recyclerSimilarMovies.visibility = View.GONE
                        }
                    }
                }

                is Resource.Error -> {

                }
            }
        })
    }
}