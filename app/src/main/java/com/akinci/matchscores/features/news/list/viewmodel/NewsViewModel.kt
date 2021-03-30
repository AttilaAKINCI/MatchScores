package com.akinci.matchscores.features.news.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinci.matchscores.common.coroutine.CoroutineContextProvider
import com.akinci.matchscores.common.helper.Resource
import com.akinci.matchscores.features.news.list.data.output.NewsResponse
import com.akinci.matchscores.features.news.list.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val coroutineContext: CoroutineContextProvider,
    private val newsRepository: NewsRepository
) : ViewModel() {

    // news list data
    private val _news = MutableLiveData<Resource<NewsResponse>>()
    val news : LiveData<Resource<NewsResponse>> = _news

    init {
        Timber.d("NewsViewModel created..")
    }

    fun fetchNews() {
        // fetch data once
        if(_news.value == null){
            viewModelScope.launch(coroutineContext.IO) {
                Timber.tag("fetchNews-VMScope").d("Top-level: current thread is ${Thread.currentThread().name}")

                delay(1000) // simulate network delay

                when(val newsResponse = newsRepository.fetchNews()){
                    is Resource.Success -> {
                        newsResponse.data?.let { data ->
                            Timber.d("News api service fetched ${data.news.size} news")
                            _news.postValue(Resource.Success(data))
                        }
                    }
                    is Resource.Error -> {
                        // error occurred while fetching news
                        _news.postValue(Resource.Error(newsResponse.message))
                    }
                }
            }
        }
    }

}