package com.akinci.matchscores.features.news.list.viewmodel

import com.akinci.matchscores.ahelpers.InstantExecutorExtension
import com.akinci.matchscores.ahelpers.TestContextProvider
import com.akinci.matchscores.common.helper.Resource
import com.akinci.matchscores.features.news.list.data.output.NewsResponse
import com.akinci.matchscores.features.news.list.repository.NewsRepository
import com.akinci.matchscores.jsonresponses.GetNews
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class NewsViewModelTest {

    @MockK
    lateinit var newsRepository: NewsRepository

    private val coroutineContext = TestContextProvider()
    private val moshi = Moshi.Builder().build()

    lateinit var newsViewModel: NewsViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        newsViewModel = NewsViewModel(coroutineContext, newsRepository)
    }

    @AfterEach
    fun tearDown() { unmockkAll() }

    @Test
    fun `fetchNews is called and returned Resource Success`() {
        coEvery { newsRepository.fetchNews() } returns Resource.Success(moshi.adapter(NewsResponse::class.java).fromJson(GetNews.news))

        newsViewModel.news.observeForever {
            when (it) {
                is Resource.Loading -> {
                    Truth.assertThat(true).isEqualTo(true)
                }
                is Resource.Success -> {
                    Truth.assertThat(it.data?.news?.get(0)?.id).isEqualTo(218533)
                    Truth.assertThat(it.data?.news?.get(0)?.title).isEqualTo("İtalya'dan Muriqi iddiası: Anlaşma tamam")
                    Truth.assertThat(it.data?.news?.get(0)?.description).isEqualTo("İtalyan basınında yer alan haberlere göre taraflar Vedat Muriqi'nin transferi için anlaşma sağladılar.")
                }
            }
        }

        // call fetch service
        newsViewModel.fetchNews()
        coroutineContext.testCoroutineDispatcher.advanceUntilIdle()
    }

    @Test
    fun `fetchNews is called and returned Resource Error`() {
        coEvery { newsRepository.fetchNews() } returns Resource.Error("Rest Call error")

        newsViewModel.news.observeForever {
            when (it) {
                is Resource.Loading -> {
                    Truth.assertThat(true).isEqualTo(true)
                }
                is Resource.Error -> {
                    Truth.assertThat(it.message).isEqualTo("Rest Call error")
                }
            }
        }

        // call fetch service
        newsViewModel.fetchNews()
        coroutineContext.testCoroutineDispatcher.advanceUntilIdle()
    }

}