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
import com.rkbapps.neetflix.adapter.person.PersonSeriesAdapter
import com.rkbapps.neetflix.databinding.FragmentPersonSeriesBinding
import com.rkbapps.neetflix.repository.person.PersonSeriesRepository
import com.rkbapps.neetflix.utils.Resource
import com.rkbapps.neetflix.viewmodelfactories.person.PersonSeriesViewModelFactory
import com.rkbapps.neetflix.viewmodels.person.PersonSeriesViewModel

class PersonSeriesFragment : Fragment() {

    private lateinit var binding: FragmentPersonSeriesBinding
    private lateinit var viewModel: PersonSeriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_person_series, container, false)
        val id = requireArguments().getInt("id")
        val repository = PersonSeriesRepository()
        viewModel = ViewModelProvider(
            requireActivity(),
            PersonSeriesViewModelFactory(repository, id)
        )[PersonSeriesViewModel::class.java]


        binding.recyclerPersonSeriesAsCast.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerPersonSeriesAsCrew.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        loadPersonSeriesData()

        return binding.root
    }

    private fun loadPersonSeriesData() {


        viewModel.personSeries.observe(viewLifecycleOwner, Observer {
            when (it) {

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null) {
                        if (it.data.body()!!.cast.isNotEmpty() && it.data.body()!!.cast != null) {
                            binding.recyclerPersonSeriesAsCast.adapter = PersonSeriesAdapter(
                                PersonMoviesAdapter.AS_CAST,
                                context,
                                it.data.body()!!.cast,
                                0
                            )
                        } else {
                            binding.recyclerPersonSeriesAsCast.visibility = View.GONE
                            binding.txtPersonAsCastSeries.visibility = View.GONE
                        }
                        if (it.data.body()!!.crew.isNotEmpty() && it.data.body()!!.crew != null) {
                            binding.recyclerPersonSeriesAsCrew.adapter = PersonSeriesAdapter(
                                PersonMoviesAdapter.AS_CREW,
                                context,
                                it.data.body()!!.crew
                            )
                        } else {
                            binding.recyclerPersonSeriesAsCrew.visibility = View.GONE
                            binding.txtPersonAsCrewSeries.visibility = View.GONE
                        }
                    }
                }

                is Resource.Error -> {
                    binding.txtPersonAsCastSeries.text = "${it.data?.code()} : ${it.message}"
                    binding.txtPersonAsCastSeries.setTextColor(Color.WHITE)
                }
            }
        })
    }
}