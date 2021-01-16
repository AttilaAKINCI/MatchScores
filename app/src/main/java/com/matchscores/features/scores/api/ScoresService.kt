package com.matchscores.features.scores.api

import com.matchscores.features.scores.model.ScoresModel
import javax.inject.Inject

class ScoresService @Inject constructor(
    private val scoreServiceInterface: ScoresServiceInterface
): ScoresServiceContract {
    override suspend fun fetchScores() : ScoresModel = scoreServiceInterface.fetchScores()
}