package com.example.covidapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.covidapp.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.covid19api.com/country/"

class MainActivity : AppCompatActivity() {
    private lateinit var countryData: CovidData
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        var covidService = retrofit.create(CovidService::class.java)

        covidService.getCountryData().enqueue(object : Callback<List<CovidData>> {
            override fun onResponse(
                call: Call<List<CovidData>>,
                response: Response<List<CovidData>>
            ) {
                Log.i("MainActivity", "onResponse $response")
                var data = response.body()
                if (data == null) {
                    Log.w("MainActivity", "Invarid response!")
                    return
                }
                countryData = data.last()
            }

            override fun onFailure(call: Call<List<CovidData>>, t: Throwable) {
                Log.e("MainActivity", "onFailure $t")
            }

        })


    }

    override fun onStart(){
        super.onStart()

        binding.country = countryData.country
        binding.Date = countryData.date
        binding.Active = countryData.Active
        binding.Confirmed = countryData.Confirmed
        binding.Deaths = countryData.Deaths
        binding.Recovered = countryData.Recovered


    }



}