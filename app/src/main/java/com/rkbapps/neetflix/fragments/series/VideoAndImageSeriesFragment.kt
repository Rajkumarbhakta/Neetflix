package com.rkbapps.neetflix.fragments.series

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.adapter.BackdropAdapter
import com.rkbapps.neetflix.adapter.PosterAdapter
import com.rkbapps.neetflix.adapter.VideoAdapter
import com.rkbapps.neetflix.databinding.FragmentVideoAndImageSeriesBinding
import com.rkbapps.neetflix.repository.series.VideoAndImageRepository
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.viewmodelfactories.series.VideoAndImageViewModelFactory
import com.rkbapps.neetflix.viewmodels.series.VideoImageViewModel

class VideoAndImageSeriesFragment : Fragment() {
    private lateinit var binding: FragmentVideoAndImageSeriesBinding
    private lateinit var viewModel: VideoImageViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_video_and_image_series,
            container,
            false
        )
        val id = arguments?.getInt("id")
        val repository = VideoAndImageRepository(RetrofitInstance.tvSeriesApi!!)
        viewModel = ViewModelProvider(
            this, VideoAndImageViewModelFactory(repository, id!!)
        )[VideoImageViewModel::class.java]

        binding.txtBackdropSeries.visibility = View.GONE
        binding.txtVideosSeries.visibility = View.GONE
        binding.txtPosterSeries.visibility = View.GONE

        binding.recyclerSeriesBackdrops.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerSeriesPosters.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerSeriesVideos.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        loadSeriesImages()
        loadSeriesVideos()
        return binding.root
    }

    private fun loadSeriesImages() {
        viewModel.images.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.posters.size != 0 && it.posters != null) {
                    binding.txtPosterSeries.visibility = View.VISIBLE
                    binding.recyclerSeriesPosters.adapter =
                        PosterAdapter(context, it.posters)
                } else {
                    binding.recyclerSeriesPosters.visibility = View.GONE
                    binding.txtPosterSeries.visibility = View.GONE
                }
                if (it.backdrops.size != 0 && it.backdrops != null) {
                    binding.txtBackdropSeries.visibility = View.VISIBLE
                    binding.recyclerSeriesBackdrops.adapter = BackdropAdapter(
                        requireContext(), it.backdrops
                    )
                } else {
                    binding.recyclerSeriesBackdrops.visibility = View.GONE
                    binding.txtBackdropSeries.visibility = View.GONE
                }

            } else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadSeriesVideos() {
        viewModel.videos.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.results.size != 0 && it.results != null) {
                    binding.txtVideosSeries.visibility = View.VISIBLE
                    binding.recyclerSeriesVideos.adapter =
                        VideoAdapter(requireContext(), it.results)
                } else {
                    binding.recyclerSeriesVideos.visibility = View.GONE
                    binding.txtVideosSeries.visibility = View.GONE
                }
            } else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}