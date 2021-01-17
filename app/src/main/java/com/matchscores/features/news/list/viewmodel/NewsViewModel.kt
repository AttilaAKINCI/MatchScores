package com.matchscores.features.news.list.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matchscores.commons.network.NetworkHelper
import com.matchscores.features.news.list.api.NewsServiceContract
import com.matchscores.features.news.list.model.NewsModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class NewsViewModel @ViewModelInject constructor(
    private val newsService: NewsServiceContract,
    networkHelper: NetworkHelper
) : ViewModel() {

    var news = MutableLiveData<NewsModel>()

    var isNodata = MutableLiveData(false)
    var isNetworkError = MutableLiveData(false)

    init {
        if (networkHelper.isNetworkConnected()) {
            // fetch data
            fetchNews()
        }else{
            // network error
            Timber.d("News List Network error..")
            isNetworkError.value = true
        }
        Timber.d("NewsViewModel created..")
    }

    private fun fetchNews() {
        isNetworkError.value = false
        isNodata.value = false
        viewModelScope.launch {
            try {
                delay(3000) // simulate network delay
                val newsModel = newsService.fetchNews()
                if(newsModel.news.isNotEmpty()){
                    news.value = newsModel
                    Timber.d("News api service fetched ${newsModel.news.size} news")
                }else{
                    Timber.d("News List nodata..")
                    isNodata.value = true
                }
            } catch (exception: Exception) {
                Timber.e("News List retrofit api error: ${exception.message}")
                isNetworkError.value = true
            }
        }
    }

}