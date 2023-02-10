package com.example.worldinalbum.retrofit

import com.example.worldinalbum.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UseRetrofit {

    val client = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun useRetrofit() : Retrofit {
        return client
    }
}