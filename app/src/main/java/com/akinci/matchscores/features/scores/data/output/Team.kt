package com.akinci.matchscores.features.scores.data.output

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team (
    val id : Int,
    val uuid : String,
    val name : String,
    val tla_name : String,
    val display_name : String
)