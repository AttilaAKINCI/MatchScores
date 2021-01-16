package com.matchscores.features.scores.model

import java.util.*

data class ScoresModel (
    val name : String,
    val format : String,
    val date : String,
    val matches : List<Match>,
    val groups : List<Objects>,
    val displayRound : Boolean
)