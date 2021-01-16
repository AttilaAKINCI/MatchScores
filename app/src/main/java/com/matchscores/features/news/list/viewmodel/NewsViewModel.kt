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
            isNetworkError.value = true
        }
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
                }else{
                    isNodata.value = true
                }
            } catch (exception: Exception) {
                isNetworkError.value = true
            }
        }
    }

}