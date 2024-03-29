package com.akinci.matchscores.features.scores.data.output

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScoresResponse (
    val name : String,
    val format : String,
    val date : String,
    val matches : List<Match>,
    val displayRound : Boolean
)