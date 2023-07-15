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
//        val responseCall: Call<MovieModel> = movieApi.getMovieDetails(id, ApiData.API_KEY)
//        responseCall.enqueue(object : Callback<MovieModel?> {
//            @SuppressLint("SetTextI18n")
//            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
//                if (response.isSuccessful) {
//                    if (response.body() != null) {
//                        if (response.body()!!.overview.isEmpty()) {
//                            binding.txtMovieOverview.text = "Not Available"
//                        } else {
//                            overview!!.text = response.body()!!.overview
//                        }
//                        if (response.body()!!.productionCompanies != null && response.body()!!.productionCompanies.size != 0) {
//                            txtProductionCompany!!.visibility = View.VISIBLE
//                            productionCompany!!.adapter = ProductionCompanyAdapter(
//                                context,
//                                response.body()!!.productionCompanies
//                            )
//                        } else {
//                            productionCompany!!.visibility = View.GONE
//                            txtProductionCompany!!.visibility = View.GONE
//                        }
//                    } else {
//                        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
//                Toast.makeText(context, "" + t.message, Toast.LENGTH_SHORT).show()
//            }
//        })
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

//        val responseCall = movieApi.getMovieCredits(id, ApiData.API_KEY)
//        responseCall.enqueue(object : Callback<CreditsModel?> {
//            override fun onResponse(call: Call<CreditsModel>, response: Response<CreditsModel>) {
//                if (response.isSuccessful) {
//                    if (response.body() != null) {
//                        if (response.body()!!.cast.size != 0 && response.body()!!.cast != null) {
//                            txtCast!!.visibility = View.VISIBLE
//                            cast!!.adapter = CastAdapter(context!!, response.body()!!.cast)
//                        } else {
//                            cast!!.visibility = View.GONE
//                            txtCast!!.visibility = View.GONE
//                        }
//                        if (response.body()!!.crew.size != 0 && response.body()!!.crew != null) {
//                            txtCrew!!.visibility = View.VISIBLE
//                            crew!!.adapter = CrewAdapter(context!!, response.body()!!.crew)
//                        } else {
//                            crew!!.visibility = View.GONE
//                            txtCrew!!.visibility = View.GONE
//                        }
//                    } else {
//                        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<CreditsModel>, t: Throwable) {
//                Toast.makeText(context, "" + t.message, Toast.LENGTH_SHORT).show()
//            }
//        })
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

//        val responseCall = movieApi.getSimilarMovies(id, ApiData.API_KEY)
//        responseCall.enqueue(object : Callback<MovieListModel?> {
//            override fun onResponse(
//                call: Call<MovieListModel>,
//                response: Response<MovieListModel>
//            ) {
//                if (response.isSuccessful) {
//                    if (response.body() != null) {
//                        if (response.body()!!.results != null && response.body()!!.results.size != 0) {
//                            txtSimilarMovies!!.visibility = View.VISIBLE
//                            similarMovies!!.adapter =
//                                MovieListChildAdapter(response.body()!!.results, context!!)
//                        } else {
//                            similarMovies!!.visibility = View.GONE
//                            txtSimilarMovies!!.visibility = View.GONE
//                        }
//                    } else {
//                        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<MovieListModel>, t: Throwable) {
//                Toast.makeText(context, "" + t.message, Toast.LENGTH_SHORT).show()
//            }
//        })
    }
}