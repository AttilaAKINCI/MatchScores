package com.akinci.matchscores.features.news.list.data.output

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse (
    val news : List<News>
)