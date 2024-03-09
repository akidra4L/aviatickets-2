package com.example.aviatickets.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * think about json deserialization considering its snake_case format
 */
data class Flight(
    @SerializedName("departure_location")
    val departureLocation: Location,
    @SerializedName("arrival_location")
    val arrivalLocation: Location,
    @SerializedName("departure_time_info")
    val departureTimeInfo: String,
    @SerializedName("arrival_time_info")
    val arrivalTimeInfo: String,
    @SerializedName("flight_number")
    val flightNumber: String,
    val airline: Airline,
    val duration: Int,
    val image: String = "https://cdn.icon-icons.com/icons2/1042/PNG/512/Airline_Mode_Icon_icon-icons.com_76429.png"
)