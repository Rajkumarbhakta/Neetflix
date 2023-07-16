package com.rkbapps.neetflix.fragments.person

import android.graphics.Color
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
import com.rkbapps.neetflix.adapter.person.PersonMoviesAdapter
import com.rkbapps.neetflix.databinding.FragmentPersonMoviesBinding
import com.rkbapps.neetflix.repository.person.PersonMoviesRepository
import com.rkbapps.neetflix.utils.Resource
import com.rkbapps.neetflix.viewmodelfactories.person.PersonMoviesViewModelFactory
import com.rkbapps.neetflix.viewmodels.person.PersonMoviesViewModel

class PersonMoviesFragment : Fragment() {

    private lateinit var binding: FragmentPersonMoviesBinding
    private lateinit var viewModel: PersonMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_person_movies, container, false)
        val id = requireArguments().getInt("id")
        val repository = PersonMoviesRepository()
        viewModel = ViewModelProvider(
            requireActivity(),
            PersonMoviesViewModelFactory(repository, id)
        )[PersonMoviesViewModel::class.java]

//        cast = view.findViewById(R.id.recyclerPersonMoviesAsCast)
//        crew = view.findViewById(R.id.recyclerPersonMoviesAsCrew)
//        asACast = view.findViewById(R.id.txtPersonAsCastMovie)
//        asACrew = view.findViewById(R.id.txtPersonAsCrewMovie)

        binding.recyclerPersonMoviesAsCast.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerPersonMoviesAsCrew.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        loadMovieCredits()

        return binding.root
    }

    private fun loadMovieCredits() {

        viewModel.personMovies.observe(viewLifecycleOwner, Observer {
            when (it) {

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        if (it.data.body()!!.cast.isNotEmpty() && it.data.body()!!.cast != null) {
                            binding.recyclerPersonMoviesAsCast.adapter = PersonMoviesAdapter(
                                PersonMoviesAdapter.AS_CAST,
                                context,
                                it.data.body()!!.cast,
                                0
                            )
                        } else {
                            binding.recyclerPersonMoviesAsCast.visibility = View.GONE
                            binding.txtPersonAsCastMovie.visibility = View.GONE
                        }
                        if (it.data.body()!!.crew.isNotEmpty() && it.data.body()!!.crew != null) {
                            binding.recyclerPersonMoviesAsCrew.adapter = PersonMoviesAdapter(
                                PersonMoviesAdapter.AS_CREW,
                                context,
                                it.data.body()!!.crew
                            )
                        } else {
                            binding.recyclerPersonMoviesAsCrew.visibility = View.GONE
                            binding.txtPersonAsCrewMovie.visibility = View.GONE
                        }
                    }
                }

                is Resource.Error -> {
                    binding.txtPersonAsCastMovie.text = "${it.data?.code()} : ${it.message}"
                    binding.txtPersonAsCastMovie.setTextColor(Color.WHITE)
                }
            }
        })


        /*

        val responseCall: Call<WorkForMovies> = personApi.getPersonMovieCredits(id, ApiData.API_KEY)
        responseCall.enqueue(object : Callback<WorkForMovies?> {
            override fun onResponse(
                call: Call<WorkForMovies?>,
                response: Response<WorkForMovies?>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        if (response.body()!!.cast.size != 0 && response.body()!!.cast != null) {
                            cast.setAdapter(
                                PersonMoviesAdapter(
                                    PersonMoviesAdapter.AS_CAST,
                                    context,
                                    response.body()!!.cast,
                                    0
                                )
                            )
                        } else {
                            cast.setVisibility(View.GONE)
                            asACast.setVisibility(View.GONE)
                        }
                        if (response.body()!!.crew.size != 0 && response.body()!!.crew != null) {
                            crew.setAdapter(
                                PersonMoviesAdapter(
                                    PersonMoviesAdapter.AS_CREW,
                                    context,
                                    response.body()!!.crew
                                )
                            )
                        } else {
                            crew.setVisibility(View.GONE)
                            asACrew.setVisibility(View.GONE)
                        }
                    } else {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<WorkForMovies?>, t: Throwable) {
                asACast.setText("Error : " + t.message)
                asACast.setTextColor(Color.WHITE)
            }
        })
         */


    }
}