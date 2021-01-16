package com.matchscores.features.scores.model

import com.matchscores.features.scores.components.ScoreType

data class Score (
    val id : Int,                       //used for unique identifier
    val headerTitleAndTime : String,    //used for header
    val type : Int,                     //0: Header / 1:Score
    val fts_A : Int,                    //score a
    val fts_B : Int,                    //score b
    val teamNameA : String,             //team name a
    val teamNameB : String              //team name b
) {
    constructor(headerTitleAndTime: String) :
            this ( -1, headerTitleAndTime, ScoreType.HEADER.ordinal, 0,0,"","")
    constructor(id: Int, fts_A: Int, fts_B: Int, teamNameA: String, teamNameB: String) :
            this (id, "", ScoreType.SCORE.ordinal, fts_A, fts_B, teamNameA, teamNameB )
}