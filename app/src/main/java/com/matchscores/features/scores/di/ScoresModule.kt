package com.matchscores.features.scores.di

import com.matchscores.features.scores.api.ScoresServiceContract
import com.matchscores.features.scores.api.ScoresService
import com.matchscores.features.scores.api.ScoresServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ScoresModule {

    /** Scores Page Dependency Integrations
     * START
     * **/
    @Provides
    @Singleton
    @Named("ScoresServiceInterface")
    fun provideScoresServiceInterface(retrofit: Retrofit) = retrofit.create(ScoresServiceInterface::class.java)

    @Provides
    @Singleton
    fun provideScoresService(
        @Named("ScoresServiceInterface") scoresServiceInterface: ScoresServiceInterface
    ) = ScoresService(scoresServiceInterface)

    @Provides
    @Singleton
    fun provideScoresServiceContract(scoresService: ScoresService): ScoresServiceContract = scoresService

}