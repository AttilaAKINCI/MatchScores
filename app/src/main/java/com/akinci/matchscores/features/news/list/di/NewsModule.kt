package com.akinci.matchscores.features.news.list.di

import com.akinci.matchscores.common.network.NetworkChecker
import com.akinci.matchscores.features.news.list.data.api.NewsServiceDao
import com.akinci.matchscores.features.news.list.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideNewsServiceDao(
        retrofit: Retrofit
    ): NewsServiceDao = retrofit.create(NewsServiceDao::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsServiceDao: NewsServiceDao,
        networkChecker: NetworkChecker
    ) = NewsRepository(newsServiceDao, networkChecker)

}