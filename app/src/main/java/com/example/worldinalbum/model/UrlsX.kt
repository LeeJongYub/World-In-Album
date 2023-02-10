package com.example.worldinalbum.model


import com.google.gson.annotations.SerializedName

data class UrlsX(
    @SerializedName("small_s3")
    val smallS3: String,
    @SerializedName("thumb")
    val thumb: String
)