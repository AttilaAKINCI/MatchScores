package com.matchscores.features.news.list.api

import com.matchscores.features.news.list.model.NewsModel

interface NewsServiceContract {
    suspend fun fetchNews() : NewsModel
}