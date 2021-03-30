package com.akinci.matchscores.features.scores.data.output

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Score (
    val id : Int,                       //used for unique identifier
    val headerTitleAndTime : String,    //used for header
    val type : Int,                     //0: Header / 1:Score
    var fts_A : Int,                    //score a
    var fts_B : Int,                    //score b
    val teamNameA : String,             //team name a
    val teamNameB : String              //team name b
)