package com.akinci.matchscores.features.news.list.repository

import com.akinci.matchscores.common.helper.Resource
import com.akinci.matchscores.common.network.NetworkChecker
import com.akinci.matchscores.common.repository.BaseRepositoryImpl
import com.akinci.matchscores.features.news.list.data.api.NewsServiceDao
import com.akinci.matchscores.features.news.list.data.output.NewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsServiceDao: NewsServiceDao,
    networkChecker: NetworkChecker
) : BaseRepositoryImpl(networkChecker) {

    suspend fun fetchNews(): Resource<NewsResponse> {
        return callService { newsServiceDao.fetchNews() }
    }
}