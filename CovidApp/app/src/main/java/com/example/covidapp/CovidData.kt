package com.example.covidapp

import com.google.gson.annotations.SerializedName
import java.util.*

data class CovidData(
    @SerializedName("Country") val country: String,
    @SerializedName("Date") val date: Date,
    @SerializedName("Confirmed") val Confirmed: Int,
    @SerializedName("Deaths") val Deaths: Int,
    @SerializedName("Recovered") val Recovered: Int,
    @SerializedName("Active") val Active: Int,
)
