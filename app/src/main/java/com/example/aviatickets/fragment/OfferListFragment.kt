package com.example.aviatickets.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aviatickets.R
import com.example.aviatickets.adapter.OfferListAdapter
import com.example.aviatickets.databinding.FragmentOfferListBinding
import com.example.aviatickets.model.entity.Offer
import com.example.aviatickets.model.network.ApiClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OfferListFragment : Fragment() {

    private val apiClient = ApiClient.instance
    private var offersList: ArrayList<Offer> = ArrayList()

    companion object {
        fun newInstance() = OfferListFragment()
    }

    private var _binding: FragmentOfferListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: OfferListAdapter by lazy {
        OfferListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        getAirlines()
    }

    private fun setupUI() {
        with(binding) {
            offerList.adapter = adapter

            sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.sort_by_price -> {
                        /**
                         * implement sorting by price
                         */
                        val sortedByPrice = offersList.sortedBy { it.price  }
                        adapter.submitList(sortedByPrice)
                    }

                    R.id.sort_by_duration -> {
                        /**
                         * implement sorting by duration
                         */
                        val sortedByDuration = offersList.sortedBy { it.flight.duration }
                        adapter.submitList(sortedByDuration)
                    }
                }
            }
        }
    }

    private fun getAirlines() {
        val response = apiClient.getOfferList()
        response.enqueue(object : Callback<ArrayList<Offer>> {
            override fun onResponse(call: Call<ArrayList<Offer>>, response: Response<ArrayList<Offer>>) {
                val offers = response.body()
                println(offers)
                if (offers != null) {
                    offersList = offers
                    adapter.submitList(offers)
                }
            }

            override fun onFailure(call: Call<ArrayList<Offer>>, t: Throwable) {
                println("HttpResponse: ${t.message}")
            }
        })
    }
}