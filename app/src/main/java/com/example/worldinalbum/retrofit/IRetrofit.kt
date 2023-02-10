package com.example.worldinalbum.retrofit

import com.example.worldinalbum.model.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {

    @GET("search/photos")
    suspend fun getSearchPhotos(
        @Query("client_id") clientId : String,
        @Query("query") searchTerm : String
    ) : Data
}