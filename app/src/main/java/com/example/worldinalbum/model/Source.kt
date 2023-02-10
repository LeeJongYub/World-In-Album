package com.example.worldinalbum.model


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("ancestry")
    val ancestry: Ancestry,
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto
)