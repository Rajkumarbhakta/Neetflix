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
import com.rkbapps.neetflix.adapter.BackdropAdapter
import com.rkbapps.neetflix.adapter.PosterAdapter
import com.rkbapps.neetflix.adapter.VideoAdapter
import com.rkbapps.neetflix.databinding.FragmentVideoAndImageMovieBinding
import com.rkbapps.neetflix.repository.movies.VideoAndImageRepository
import com.rkbapps.neetflix.utils.Resource
import com.rkbapps.neetflix.viewmodelfactories.movies.VideoAndImagesViewModelFactory
import com.rkbapps.neetflix.viewmodels.movies.VideoAndImageViewModel

class VideoAndImageMovieFragment : Fragment() {
    private lateinit var binding: FragmentVideoAndImageMovieBinding
    private lateinit var viewModel: VideoAndImageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_video_and_image_movie,
            container,
            false
        )
        val id = requireArguments().getInt("id")
        val repository = VideoAndImageRepository()

        viewModel = ViewModelProvider(
            requireActivity(),
            VideoAndImagesViewModelFactory(repository, id)
        )[VideoAndImageViewModel::class.java]

        binding.txtBackdropMovie.visibility = View.GONE
        binding.txtPosterMovie.visibility = View.GONE
        binding.txtVideosMovie.visibility = View.GONE

        binding.recyclerMovieBackdrops.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerMoviePosters.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerMovieVideos.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        loadImages(id)
        loadVideos(id)
        return binding.root
    }

    private fun loadImages(id: Int) {

        viewModel.images.observe(viewLifecycleOwner, Observer {

            when (it) {

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        if (it.data.body()!!.posters.size != 0 && it.data.body()!!.posters != null) {
                            binding.txtPosterMovie.visibility = View.VISIBLE
                            binding.recyclerMoviePosters.adapter =
                                PosterAdapter(context, it.data.body()!!.posters)
                        } else {
                            binding.recyclerMoviePosters.visibility = View.GONE
                            binding.txtPosterMovie.visibility = View.GONE
                        }
                        if (it.data.body()!!.backdrops.size != 0 && it.data.body()!!.backdrops != null) {
                            binding.txtBackdropMovie.visibility = View.VISIBLE
                            binding.recyclerMovieBackdrops.adapter = BackdropAdapter(
                                requireContext(), it.data.body()!!.backdrops
                            )
                        } else {
                            binding.recyclerMovieBackdrops.visibility = View.GONE
                            binding.txtBackdropMovie.visibility = View.GONE
                        }
                    }
                }

                is Resource.Error -> {

                }


            }


        })
    }

    private fun loadVideos(id: Int) {

        viewModel.videos.observe(viewLifecycleOwner, Observer {

            when (it) {

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        if (it.data.body()!!.results.size != 0 && it.data.body()!!.results != null) {
                            binding.txtVideosMovie.visibility = View.VISIBLE
                            binding.recyclerMovieVideos.adapter =
                                VideoAdapter(context, it.data.body()!!.results)
                        } else {
                            binding.txtVideosMovie.visibility = View.GONE
                            binding.recyclerMovieVideos.visibility = View.GONE
                        }
                    }
                }

                is Resource.Error -> {

                }
            }
        })
    }
}