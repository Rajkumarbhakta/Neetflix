package com.rkbapps.neetflix.fragments.movie

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
import com.rkbapps.neetflix.adapter.ReviewAdapter
import com.rkbapps.neetflix.databinding.FragmentReviewsMovieBinding
import com.rkbapps.neetflix.repository.movies.ReviewsMovieFragmentRepository
import com.rkbapps.neetflix.utils.Resource
import com.rkbapps.neetflix.viewmodelfactories.movies.ReviewsMovieFragmentViewModelFactory
import com.rkbapps.neetflix.viewmodels.movies.ReviewsMovieFragmentViewModel

class ReviewsMovieFragment : Fragment() {

    private lateinit var binding: FragmentReviewsMovieBinding
    private lateinit var viewModel: ReviewsMovieFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_reviews_movie, container, false)
        val id = requireArguments().getInt("id")
        val repository = ReviewsMovieFragmentRepository()

        viewModel = ViewModelProvider(
            requireActivity(),
            ReviewsMovieFragmentViewModelFactory(repository, id)
        )[ReviewsMovieFragmentViewModel::class.java]


        binding.txtNoMovieReview.visibility = View.GONE
        binding.recyclerReview.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        loadReviews()

        return binding.root
    }


    private fun loadReviews() {

        viewModel.reviews.observe(viewLifecycleOwner, Observer {
            when (it) {

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        if (it.data.body()!!.results == null || it.data.body()!!.results.size == 0) {
                            binding.txtNoMovieReview.visibility = View.VISIBLE
                        } else {
                            binding.recyclerReview.adapter =
                                ReviewAdapter(context, it.data.body()!!.results)
                        }
                    }

                }

                is Resource.Error -> {

                }


            }
        })


    }


}