package com.example.jt_project.models

import com.google.gson.annotations.SerializedName

data class Owner (
    @SerializedName("id")
    val id : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("firstName")
    val firstName : String,
    @SerializedName("lastName")
    val lastName : String,
    @SerializedName("picture")
    val picture : String
)