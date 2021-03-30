package com.akinci.matchscores.features.scores.data.output

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Match (
    val id : Int,
    val date_time_utc : String,
    val match_time : String,
    val status : String,
    val fts_A : Int,
    val fts_B : Int,
    val hts_A : Int,
    val hts_B : Int,
    val team_A : Team,
    val team_B : Team,
    val extras : Extra
)