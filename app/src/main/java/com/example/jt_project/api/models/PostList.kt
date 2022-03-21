package com.example.jt_project.api.models

import com.google.gson.annotations.SerializedName

data class PostList (
    @SerializedName("data")
    val data : List<Data>,
    @SerializedName("total")
    val total : Int,
    @SerializedName("page")
    val page : Int,
    @SerializedName("limit")
    val limit : Int
)