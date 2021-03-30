package com.akinci.matchscores.features.news.list.data.api

import com.akinci.matchscores.common.network.RestConfig
import com.akinci.matchscores.features.news.list.data.output.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsServiceDao {

    @GET(RestConfig.NEWS_ENDPOINT)
    suspend fun fetchNews() : Response<NewsResponse>

}