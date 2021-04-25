package com.akinci.matchscores.features.scores.viewmodel

import com.akinci.matchscores.ahelpers.InstantExecutorExtension
import com.akinci.matchscores.ahelpers.TestContextProvider
import com.akinci.matchscores.common.helper.Resource
import com.akinci.matchscores.features.scores.data.output.ScoresResponse
import com.akinci.matchscores.features.scores.repository.ScoresRepository
import com.akinci.matchscores.jsonresponses.GetScores
import com.akinci.matchscores.jsonresponses.GetScoresEmpty
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class ScoresViewModelTest {

    @MockK
    lateinit var scoresRepository: ScoresRepository

    private val coroutineContext = TestContextProvider()
    private val moshi = Moshi.Builder().build()

    lateinit var scoresViewModel: ScoresViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        scoresViewModel = ScoresViewModel(coroutineContext, scoresRepository)
    }

    @AfterEach
    fun tearDown() { unmockkAll() }

    @Test
    fun `fetchScores is called and returned Resource Success`() {
        coEvery { scoresRepository.fetchScores() } returns Resource.Success(moshi.adapter(ScoresResponse::class.java).fromJson(GetScores.scores))

        scoresViewModel.scores.observeForever {
            when (it) {
                is Resource.Loading -> {
                    Truth.assertThat(true).isEqualTo(true)
                }
                is Resource.Success -> { /** in 0 header view data is placed so start with 1. index **/
                    Truth.assertThat(it.data?.get(1)?.id).isEqualTo(3161474)
                    Truth.assertThat(it.data?.get(1)?.fts_A).isEqualTo(1)
                    Truth.assertThat(it.data?.get(1)?.fts_B).isEqualTo(2)
                    Truth.assertThat(it.data?.get(1)?.teamNameA).isEqualTo("Astana")
                    Truth.assertThat(it.data?.get(1)?.teamNameB).isEqualTo("Partizan")
                }
            }
        }

        // call fetch service
        scoresViewModel.fetchScores()
        coroutineContext.testCoroutineDispatcher.advanceUntilIdle()
    }

    @Test
    fun `fetchScores is called and returned Resource Error`() {
        coEvery { scoresRepository.fetchScores() } returns Resource.Error("fetchScore rest service error")

        scoresViewModel.scores.observeForever {
            when (it) {
                is Resource.Loading -> {
                    Truth.assertThat(true).isEqualTo(true)
                }
                is Resource.Error -> {
                    Truth.assertThat(it.message).isEqualTo("fetchScore rest service error")
                }
            }
        }

        // call fetch service
        scoresViewModel.fetchScores()
        coroutineContext.testCoroutineDispatcher.advanceUntilIdle()
    }

    @Test
    fun `fetchScores is called and returned Resource Info`() {
        coEvery { scoresRepository.fetchScores() } returns Resource.Success(moshi.adapter(ScoresResponse::class.java).fromJson(GetScoresEmpty.scores))

        scoresViewModel.scores.observeForever {
            when (it) {
                is Resource.Loading -> {
                    Truth.assertThat(true).isEqualTo(true)
                }
                is Resource.Info -> {
                    Truth.assertThat(it.message).isEqualTo("There is not any score data")
                }
            }
        }

        // call fetch service
        scoresViewModel.fetchScores()
        coroutineContext.testCoroutineDispatcher.advanceUntilIdle()
    }

}