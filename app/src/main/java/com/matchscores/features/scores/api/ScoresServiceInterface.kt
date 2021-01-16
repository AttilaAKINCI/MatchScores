package com.matchscores.features.scores.api

import com.matchscores.commons.di.RestConfig
import com.matchscores.features.scores.model.ScoresModel
import retrofit2.http.GET

interface ScoresServiceInterface {
    @GET(RestConfig.MATCHES_ENDPOINT)
    suspend fun fetchScores() : ScoresModel
}