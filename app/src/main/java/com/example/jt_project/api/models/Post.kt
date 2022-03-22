package com.example.jt_project.api.models

import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDateTime

data class Post(
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
){
    val date
        get() =  LocalDateTime.parse(publishDate, org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ"))

}
