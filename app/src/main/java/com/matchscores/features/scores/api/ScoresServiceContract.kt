package com.matchscores.features.scores.api

import com.matchscores.features.scores.model.ScoresModel

interface ScoresServiceContract {
    suspend fun fetchScores() : ScoresModel
}
