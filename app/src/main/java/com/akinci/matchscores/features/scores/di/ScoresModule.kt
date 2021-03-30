package com.akinci.matchscores.features.scores.di

import com.akinci.matchscores.common.network.NetworkChecker
import com.akinci.matchscores.features.scores.data.api.ScoresServiceDao
import com.akinci.matchscores.features.scores.repository.ScoresRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScoresModule {

    @Provides
    @Singleton
    fun provideScoresServiceDao(
        retrofit: Retrofit
    ): ScoresServiceDao = retrofit.create(ScoresServiceDao::class.java)

    @Provides
    @Singleton
    fun provideScoresRepository(
        scoresServiceDao: ScoresServiceDao,
        networkChecker: NetworkChecker
    ) = ScoresRepository(scoresServiceDao, networkChecker)

}