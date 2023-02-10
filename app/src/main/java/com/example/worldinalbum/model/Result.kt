package com.example.worldinalbum.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("tags")
    val tags: List<Tag>,
    @SerializedName("urls")
    val urls: UrlsX,
    @SerializedName("user")
    val user: UserX
)