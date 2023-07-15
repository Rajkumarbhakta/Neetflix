package com.rkbapps.neetflix.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.databinding.FragmentDiscoverBinding

class DiscoverFragment : Fragment() {
    private lateinit var binding: FragmentDiscoverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_discover, container, false)
        val movies = binding.navMovies
        val tvSeries = binding.navTvSeries

        loadFragment(MoviesFragment())

        movies.setOnClickListener {
            movies.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.selected_type_background
            )
            tvSeries.background = null
            loadFragment(MoviesFragment())
        }
        tvSeries.setOnClickListener {
            tvSeries.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.selected_type_background
            )
            movies.background = null
            loadFragment(TvSeriesFragment())
        }
        return binding.root
    }

    private fun loadFragment(fragment: Fragment?) {
        childFragmentManager.beginTransaction().replace(binding.containerFrag.id, fragment!!)
            .commit()
    }
}