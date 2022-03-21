package com.example.jt_project.api.models

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("text")
    val text: String,
    @SerializedName("publishDate")
    val publishDate: String,
    @SerializedName("owner")
    val owner: Owner
)
