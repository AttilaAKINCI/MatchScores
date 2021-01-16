package com.matchscores.features.news.list.api

import com.matchscores.commons.di.RestConfig
import com.matchscores.features.news.list.model.NewsModel
import retrofit2.http.GET

interface NewsServiceInterface {
    @GET(RestConfig.NEWS_ENDPOINT)
    suspend fun fetchNews() : NewsModel
}