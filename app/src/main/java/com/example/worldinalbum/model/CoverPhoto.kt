package com.example.worldinalbum.model


import com.google.gson.annotations.SerializedName

data class CoverPhoto(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("urls")
    val urls: UrlsX,
    @SerializedName("user")
    val user: User
)