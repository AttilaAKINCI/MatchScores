package com.matchscores.features.news.list.di

import com.matchscores.features.news.list.api.NewsServiceInterface
import com.matchscores.features.news.list.api.NewsServiceContract
import com.matchscores.features.news.list.api.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NewsModule {

    /** News Page Dependency Integrations
     * START
     * **/
    @Provides
    @Singleton
    @Named("NewsServiceInterface")
    fun provideNewsServiceInterface(retrofit: Retrofit) = retrofit.create(NewsServiceInterface::class.java)

    @Provides
    @Singleton
    fun provideNewsService(
        @Named("NewsServiceInterface") newsServiceInterface: NewsServiceInterface) = NewsService(newsServiceInterface)

    @Provides
    @Singleton
    fun provideNewsServiceContract(newsService: NewsService): NewsServiceContract = newsService

}