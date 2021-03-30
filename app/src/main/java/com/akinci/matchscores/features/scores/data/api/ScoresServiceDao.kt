package com.akinci.matchscores.features.scores.data.api

import com.akinci.matchscores.common.network.RestConfig
import com.akinci.matchscores.features.scores.data.output.ScoresResponse
import retrofit2.Response
import retrofit2.http.GET

interface ScoresServiceDao {

    @GET(RestConfig.MATCHES_ENDPOINT)
    suspend fun fetchScores() : Response<ScoresResponse>

}