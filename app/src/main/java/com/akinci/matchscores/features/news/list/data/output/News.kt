package com.akinci.matchscores.features.news.list.data.output

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class News (
    val id : Int,
    val title : String,
    val description : String,
    val date : String,
    val link : String,
    val picUrl : String
)