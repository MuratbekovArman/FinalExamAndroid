package com.example.covidapp

import retrofit2.Call
import retrofit2.http.GET

interface CovidService {

    @GET("India")
    fun getCountryData(): Call<List<CovidData>>

    @GET("India")
    fun getStatesData(): Call<List<CovidData>>
}