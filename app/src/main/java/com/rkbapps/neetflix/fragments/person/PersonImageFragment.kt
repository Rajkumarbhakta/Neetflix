package com.rkbapps.neetflix.fragments.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.adapter.person.PersonImageAdapter
import com.rkbapps.neetflix.databinding.FragmentPersonImageBinding
import com.rkbapps.neetflix.repository.person.PersonImageRepository
import com.rkbapps.neetflix.utils.Resource
import com.rkbapps.neetflix.viewmodelfactories.person.PersonImageViewModelFactory
import com.rkbapps.neetflix.viewmodels.person.PersonImageViewModel

class PersonImageFragment : Fragment() {

    private lateinit var binding: FragmentPersonImageBinding
    private lateinit var viewModel: PersonImageViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_person_image, container, false)
        val id = requireArguments().getInt("id")
        val repository = PersonImageRepository()
        viewModel = ViewModelProvider(
            requireActivity(),
            PersonImageViewModelFactory(repository, id)
        )[PersonImageViewModel::class.java]

        binding.recyclerPersonImage.layoutManager = GridLayoutManager(context, 2)

        loadImages()
        return binding.root
    }

    private fun loadImages() {

        viewModel.images.observe(viewLifecycleOwner, Observer {
            when (it) {

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    if (it.data?.body() != null)
                        binding.recyclerPersonImage.adapter =
                            PersonImageAdapter(requireContext(), it.data.body()!!.profiles);
                }

                is Resource.Error -> {

                }
            }
        })


    }


}