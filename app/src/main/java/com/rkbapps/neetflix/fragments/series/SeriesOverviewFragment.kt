package com.rkbapps.neetflix.fragments.series

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
import com.rkbapps.neetflix.adapter.ProductionCompanyAdapter
import com.rkbapps.neetflix.adapter.TvSeriesListChildAdapter
import com.rkbapps.neetflix.adapter.credit.CastAdapter
import com.rkbapps.neetflix.adapter.credit.CrewAdapter
import com.rkbapps.neetflix.adapter.series.SeasonAdapter
import com.rkbapps.neetflix.databinding.FragmentSeriesOverviewBinding
import com.rkbapps.neetflix.repository.series.SeriesOverviewRepository
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.viewmodelfactories.series.SeriesOverviewViewModelFactory
import com.rkbapps.neetflix.viewmodels.series.SeriesOverviewViewModel

class SeriesOverviewFragment : Fragment() {

    private lateinit var binding: FragmentSeriesOverviewBinding
    private lateinit var viewModel: SeriesOverviewViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_series_overview,
            container,
            false
        )
        val id = arguments?.getInt("id")!!
        val repository = SeriesOverviewRepository(RetrofitInstance.tvSeriesApi!!)
        viewModel = ViewModelProvider(
            this,
            SeriesOverviewViewModelFactory(repository, id)
        )[SeriesOverviewViewModel::class.java]

        binding.txtCastSeriesOverview.visibility = View.GONE
        binding.txtCrewSeriesOverview.visibility = View.GONE
        binding.txtNetworkSeriesOverview.visibility = View.GONE
        binding.txtProductionCompanySeriesOverview.visibility = View.GONE
        binding.txtSeasons.visibility = View.GONE
        binding.txtSimilarSeries.visibility = View.GONE

        binding.recyclerSeasons.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerCrewSeries.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerCastSeries.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerProductionCompanySeries.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.HORIZONTAL,
            false
        )
        binding.recyclerNetwork.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerSimilarSeries.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        loadSeriesData(id)
        loadSeriesCredits()
        loadSimilarSeries()
        return binding.root
    }

    private fun loadSeriesData(tvId:Int) {

        viewModel.seriesDetails.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.txtSeriesOverview.text = it.overview
                binding.txtSeriesStatus.text = it.status
                binding.txtSeriesType.text = it.type
                binding.txtFirstAirDate.text = it.firstAirDate
                binding.lastAirDate.text = it.lastAirDate
                if (it.seasons.isNotEmpty() && it.seasons != null) {
                    binding.txtSeasons.visibility = View.VISIBLE
                    binding.recyclerSeasons.adapter =
                        SeasonAdapter(requireContext(), it.seasons, tvId)
                } else {
                    binding.recyclerSeasons.visibility = View.GONE
                }
                if (it.networks.isNotEmpty() && it.networks != null) {
                    binding.txtNetworkSeriesOverview.visibility = View.VISIBLE
                    binding.recyclerNetwork.adapter =
                        ProductionCompanyAdapter(requireContext(), it.networks)
                } else {
                    binding.recyclerNetwork.visibility = View.GONE
                }
                if (it.productionCompanies.isNotEmpty() && it.productionCompanies != null) {
                    binding.txtProductionCompanySeriesOverview.visibility = View.VISIBLE
                    binding.recyclerProductionCompanySeries.adapter =
                        ProductionCompanyAdapter(requireContext(), it.productionCompanies)
                } else {
                    binding.recyclerProductionCompanySeries.visibility = View.GONE
                }

            } else {
                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
            }
        }


//        val responseCall = tvSeriesApi!!.getSeriesDetails(id, ApiData.API_KEY)
//        responseCall!!.enqueue(object : Callback<TvSeriesModel?> {
//            override fun onResponse(
//                call: Call<TvSeriesModel?>,
//                response: Response<TvSeriesModel?>,
//            ) {
//                if (response.isSuccessful) {
//                    if (response.body() == null) {
//                        Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
//                    } else {
//                        val tvSeriesModel = response.body()
//                        overview!!.text = tvSeriesModel!!.overview
//                        status!!.text = tvSeriesModel.status
//                        type!!.text = tvSeriesModel.type
//                        firstAirDate!!.text = tvSeriesModel.firstAirDate
//                        lastAirDate!!.text = tvSeriesModel.lastAirDate
//                        if (tvSeriesModel.seasons.size != 0 && tvSeriesModel.seasons != null) {
//                            txtSeason!!.visibility = View.VISIBLE
//                            seasons!!.adapter = SeasonAdapter(context!!, tvSeriesModel.seasons, id)
//                        } else {
//                            seasons!!.visibility = View.GONE
//                        }
//                        if (tvSeriesModel.networks.size != 0 && tvSeriesModel.networks != null) {
//                            txtNetwork!!.visibility = View.VISIBLE
//                            network!!.adapter =
//                                ProductionCompanyAdapter(context, tvSeriesModel.networks)
//                        } else {
//                            network!!.visibility = View.GONE
//                        }
//                        if (tvSeriesModel.productionCompanies.size != 0 && tvSeriesModel.productionCompanies != null) {
//                            txtProductionCo!!.visibility = View.VISIBLE
//                            productionCompanies!!.adapter =
//                                ProductionCompanyAdapter(context, tvSeriesModel.productionCompanies)
//                        } else {
//                            productionCompanies!!.visibility = View.GONE
//                        }
//                    }
//                } else {
//                    Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<TvSeriesModel?>, t: Throwable) {
//                Toast.makeText(context, "" + t.message, Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun loadSeriesCredits() {

        viewModel.seriesCredits.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.cast.size != 0 && it.cast != null) {
                    binding.txtCastSeriesOverview.visibility = View.VISIBLE
                    binding.recyclerCastSeries.adapter = CastAdapter(requireContext(), it.cast)
                } else {
                    binding.txtCastSeriesOverview.visibility = View.GONE
                    binding.recyclerCastSeries.visibility = View.GONE
                }

                if (it.crew.size != 0 && it.crew != null) {
                    binding.txtCrewSeriesOverview.visibility = View.VISIBLE
                    binding.recyclerCrewSeries.adapter = CrewAdapter(requireContext(), it.crew)
                } else {
                    binding.recyclerCrewSeries.visibility = View.GONE
                    binding.txtCrewSeriesOverview.visibility = View.GONE
                }

            } else {
                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
            }

        }

//        val responseCall = tvSeriesApi!!.getSeriesCredits(id, ApiData.API_KEY)
//        responseCall!!.enqueue(object : Callback<CreditsModel?> {
//            override fun onResponse(call: Call<CreditsModel?>, response: Response<CreditsModel?>) {
//                if (response.isSuccessful) {
//                    if (response.body() != null) {
//                        if (response.body()!!.cast.size != 0 && response.body()!!.cast != null) {
//                            txtCast!!.visibility = View.VISIBLE
//                            cast!!.adapter = CastAdapter(context!!, response.body()!!.cast)
//                        } else {
//                            txtCast!!.visibility = View.GONE
//                            cast!!.visibility = View.GONE
//                        }
//                        if (response.body()!!.crew.size != 0 && response.body()!!.crew != null) {
//                            txtCrew!!.visibility = View.VISIBLE
//                            crew!!.adapter = CrewAdapter(context!!, response.body()!!.crew)
//                        } else {
//                            crew!!.visibility = View.GONE
//                            txtCrew!!.visibility = View.GONE
//                        }
//                    }
//                } else {
//                    Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<CreditsModel?>, t: Throwable) {
//                Toast.makeText(context, "" + t.message, Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun loadSimilarSeries() {

        viewModel.seriesSimilar.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it.results.isNotEmpty() && it.results != null) {
                    binding.txtSimilarSeries.visibility = View.VISIBLE
                    binding.recyclerSimilarSeries.adapter =
                        TvSeriesListChildAdapter(it.results, requireContext())
                } else {
                    binding.recyclerSimilarSeries.visibility = View.GONE
                    binding.txtSimilarSeries.visibility = View.GONE
                }
            }

//        val responseCall = tvSeriesApi!!.getSimilarTvSeries(id, ApiData.API_KEY)
//        responseCall!!.enqueue(object : Callback<TvSeriesListModel?> {
//            override fun onResponse(
//                call: Call<TvSeriesListModel?>,
//                response: Response<TvSeriesListModel?>,
//            ) {
//                if (response.isSuccessful) {
//                    if (response.body() != null) {
//                        if (response.body()!!.results != null && response.body()!!.results.size != 0) {
//                            txtSimilarSeries!!.visibility = View.VISIBLE
//                            similarSeries!!.adapter =
//                                TvSeriesListChildAdapter(response.body()!!.results, context!!)
//                        } else {
//                            similarSeries!!.visibility = View.GONE
//                            txtSimilarSeries!!.visibility = View.GONE
//                        }
//                    }
//                } else {
//                    Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<TvSeriesListModel?>, t: Throwable) {
//                Toast.makeText(context, "" + t.message, Toast.LENGTH_SHORT).show()
//            }
//        })
        }
    }
}