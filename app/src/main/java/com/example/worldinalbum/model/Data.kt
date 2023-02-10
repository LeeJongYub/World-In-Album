package com.example.worldinalbum.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("results")
    val results: List<Result>
)