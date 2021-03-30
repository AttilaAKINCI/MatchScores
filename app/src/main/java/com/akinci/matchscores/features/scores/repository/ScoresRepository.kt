package com.akinci.matchscores.features.scores.repository

import com.akinci.matchscores.common.helper.Resource
import com.akinci.matchscores.common.network.NetworkChecker
import com.akinci.matchscores.common.repository.BaseRepositoryImpl
import com.akinci.matchscores.features.scores.data.api.ScoresServiceDao
import com.akinci.matchscores.features.scores.data.output.ScoresResponse
import javax.inject.Inject

class ScoresRepository @Inject constructor(
    private val scoresServiceDao: ScoresServiceDao,
    networkChecker: NetworkChecker
) : BaseRepositoryImpl(networkChecker) {

    suspend fun fetchScores(): Resource<ScoresResponse> {
        return callService { scoresServiceDao.fetchScores() }
    }

}