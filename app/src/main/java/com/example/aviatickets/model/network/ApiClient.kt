package com.example.aviatickets.model.network

import com.example.aviatickets.model.service.AirlineService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://my-json-server.typicode.com/estharossa/fake-api-demo/"

object ApiClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory
                .create()
        )
        .build()

    val instance: AirlineService = retrofit.create(AirlineService::class.java)
}