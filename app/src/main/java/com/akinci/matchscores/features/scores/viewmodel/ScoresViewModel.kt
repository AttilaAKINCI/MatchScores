package com.akinci.matchscores.features.scores.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinci.matchscores.common.coroutine.CoroutineContextProvider
import com.akinci.matchscores.common.helper.Resource
import com.akinci.matchscores.features.news.list.data.output.NewsResponse
import com.akinci.matchscores.features.scores.data.output.Score
import com.akinci.matchscores.features.scores.repository.ScoresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ObsoleteCoroutinesApi
@HiltViewModel
class ScoresViewModel @Inject constructor(
    private val coroutineContext: CoroutineContextProvider,
    private val scoresRepository: ScoresRepository
) : ViewModel() {

    // score table data
    private val _scores = MutableLiveData<Resource<Score>>()
    val scores : LiveData<Resource<Score>> = _scores

    private var tickerChannel = ticker(delayMillis = 30_000, initialDelayMillis = 0)
    override fun onCleared() {
        tickerChannel.cancel()
        super.onCleared()
    }

    init {
        Timber.d("ScoresViewModel created..")
    }

    fun fetchScores() {
        // fetch scores once
        if(_scores.value == null){
            viewModelScope.launch(coroutineContext.IO) {
                Timber.tag("fetchMatches-VMScope").d("Top-level: current thread is ${Thread.currentThread().name}")

                delay(1000) // simulate network delay

                when (val scoresModel = scoresRepository.fetchScores()) {
                    is Resource.Success -> {

                    }
                    is Resource.Error -> {
                        // error occurred while fetching scores
                        _scores.postValue(Resource.Error(scoresModel.message))
                    }
                }
            }
        }

        viewModelScope.launch(coroutineContext.IO) {
            Timber.tag("fetchNews-VMScope").d("Top-level: current thread is ${Thread.currentThread().name}")

            delay(1000) // simulate network delay

            when(val scoresModel = scoresRepository.fetchScores()) {
                is Resource.Success -> {

                }
                is Resource.Error -> {
                    // error occurred while fetching scores
                    _news.postValue(Resource.Error(scoresModel.message))
                }
            }

            try {

                for (event in tickerChannel) {

                    if(scoresModel.matches.isNotEmpty()){

                        val localScore = mutableListOf<Score>()
                        //provide header data
                        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("tr")).parse(scoresModel.date)
                        var formattedDate = ""
                        date?.let {
                            formattedDate = SimpleDateFormat("d MMMM y", Locale("tr")).format(date)
                        }

                        val title = "${scoresModel.name} - $formattedDate"
                        localScore.add(Score(title))

                        scoresModel.matches.map {
                            localScore.add(
                                Score(
                                it.id,
                                it.fts_A,
                                it.fts_B,
                                it.team_A.name,
                                it.team_B.name
                            )
                            )
                        }

                        scores.value = localScore
                        Timber.d("Score api service fetched ${scoresModel.matches.size} matches")
                    }else{
                        Timber.d("News List no data..")
                        isNodata.value = true
                    }
                }
            } catch (exception: Exception) {
                Timber.e("News List retrofit api error : ${exception.message}")
                isNetworkError.value = true
            }
        }

    }


}