package com.matchscores.features.news.list.api

import com.matchscores.features.news.list.model.NewsModel
import javax.inject.Inject

class NewsService @Inject constructor(
    private val newsServiceInterface: NewsServiceInterface
):NewsServiceContract{
    override suspend fun fetchNews() : NewsModel = newsServiceInterface.fetchNews()
}