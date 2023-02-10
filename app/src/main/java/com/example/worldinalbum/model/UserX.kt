package com.example.worldinalbum.model


import com.google.gson.annotations.SerializedName

data class UserX(
    @SerializedName("id")
    val id: String,
    @SerializedName("social")
    val social: Social,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("username")
    val username: String
)