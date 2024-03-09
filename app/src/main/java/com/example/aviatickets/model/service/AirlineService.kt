package com.example.aviatickets.model.service

import com.example.aviatickets.model.entity.Offer
import retrofit2.Call
import retrofit2.http.GET

interface AirlineService {
    @GET("offer_list")
    fun getOfferList(): Call<ArrayList<Offer>>
}