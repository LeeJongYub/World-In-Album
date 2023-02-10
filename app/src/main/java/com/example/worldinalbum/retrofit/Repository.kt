package com.example.worldinalbum.retrofit

import com.example.worldinalbum.constants.Constants

class Repository {

    val client = UseRetrofit.useRetrofit().create(IRetrofit::class.java)

    suspend fun repositoryGetPhoto(searchTerm : String) = client.getSearchPhotos(Constants.CLIENT_ID, searchTerm)
}