package com.rkbapps.neetflix.fragments.movie

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.rkbapps.neetflix.models.images.ImagesModel
import com.rkbapps.neetflix.repository.movies.VideoAndImageRepository
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.utils.Resource
import com.rkbapps.neetflix.viewmodelfactories.movies.VideoAndImagesViewModelFactory
import com.rkbapps.neetflix.viewmodels.movies.VideoAndImageViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


//        val responseCall = movieApi.getMovieImages(id, ApiData.API_KEY)
//        responseCall.enqueue(object : Callback<ImagesModel?> {
//            override fun onResponse(call: Call<ImagesModel>, response: Response<ImagesModel>) {
//                if (response.isSuccessful) {
//                    if (response.body() != null) {
//                        if (response.body()!!.posters.size != 0 && response.body()!!.posters != null) {
//                            txtPoster!!.visibility = View.VISIBLE
//                            posters!!.adapter = PosterAdapter(context, response.body()!!.posters)
//                        } else {
//                            posters!!.visibility = View.GONE
//                            txtPoster!!.visibility = View.GONE
//                        }
//                        if (response.body()!!.backdrops.size != 0 && response.body()!!.backdrops != null) {
//                            txtBackdrop!!.visibility = View.VISIBLE
//                            backdrops!!.adapter = BackdropAdapter(
//                                context!!, response.body()!!.backdrops
//                            )
//                        } else {
//                            backdrops!!.visibility = View.GONE
//                            txtBackdrop!!.visibility = View.GONE
//                        }
//                    }
//                } else {
//                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ImagesModel>, t: Throwable) {
//                txtPoster!!.visibility = View.VISIBLE
//                txtPoster!!.setTextColor(Color.WHITE)
//                txtPoster!!.text = t.message
//            }
//        })
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


//        movieApi.getMovieVideos(id, ApiData.API_KEY)
//            .enqueue(object : Callback<VideoModel?> {
//                override fun onResponse(call: Call<VideoModel>, response: Response<VideoModel>) {
//                    if (response.isSuccessful) {
//                        if (response.body() != null) {
//                            if (response.body()!!.results.size != 0 && response.body()!!.results != null) {
//                                txtVideos!!.visibility = View.VISIBLE
//                                videos!!.adapter = VideoAdapter(context, response.body()!!.results)
//                            } else {
//                                txtVideos!!.visibility = View.GONE
//                                videos!!.visibility = View.GONE
//                            }
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<VideoModel>, t: Throwable) {}
//            })
    }
}