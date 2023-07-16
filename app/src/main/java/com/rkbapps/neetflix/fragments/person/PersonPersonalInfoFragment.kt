package com.rkbapps.neetflix.fragments.person

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.R
import com.rkbapps.neetflix.databinding.FragmentPersonPersonalInfoBinding
import com.rkbapps.neetflix.models.person.PersonDetails
import com.rkbapps.neetflix.repository.person.PersonalDetailsRepository
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import com.rkbapps.neetflix.viewmodelfactories.person.PersonalDetailsViewModelFactory
import com.rkbapps.neetflix.viewmodels.person.PersonalDetailViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonPersonalInfoFragment : Fragment() {
    private lateinit var binding: FragmentPersonPersonalInfoBinding
    private lateinit var viewModel: PersonalDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_person_personal_info,
            container,
            false
        )
        val id = requireArguments().getInt("id")
        val repository = PersonalDetailsRepository()
        viewModel = ViewModelProvider(
            requireActivity(),
            PersonalDetailsViewModelFactory(repository, id)
        )[PersonalDetailViewModel::class.java]

        loadPersonalDetails()

        return binding.root
    }

    private fun loadPersonalDetails() {
        viewModel.personalDetails.observe(viewLifecycleOwner, Observer {
            when (it) {

                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    if (it.data?.body() != null) {

                        if (it.data.body()!!.biography.isNullOrEmpty()) {
                            binding.biography = "Not Available"
                        } else {
                            binding.biography = it.data.body()!!.biography
                        }
                    }
                }
                is Resource.Error -> {
                    binding.biography = "${it.data?.code()} : ${it.message}"
                }
            }
        })
    }
}