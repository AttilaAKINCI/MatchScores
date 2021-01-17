package com.matchscores.features.scores.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matchscores.commons.network.NetworkHelper
import com.matchscores.features.scores.api.ScoresServiceContract
import com.matchscores.features.scores.model.Score
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@ObsoleteCoroutinesApi
class ScoresViewModel @ViewModelInject constructor(
        private val scoresService: ScoresServiceContract,
        networkHelper: NetworkHelper
) : ViewModel() {

    var scores = MutableLiveData<MutableList<Score>>()

    var isNodata = MutableLiveData(false)
    var isNetworkError = MutableLiveData(false)

    @ObsoleteCoroutinesApi
    private var tickerChannel = ticker(delayMillis = 30_000, initialDelayMillis = 0)

    init {
        if (networkHelper.isNetworkConnected()) {
            // fetch data
            fetchMatches()
        }else{
            // network error
            Timber.d("News List Network error..")
            isNetworkError.value = true
        }
        Timber.d("ScoresViewModel created..")
    }

    @ObsoleteCoroutinesApi
    override fun onCleared() {
        tickerChannel.cancel()
        super.onCleared()
    }

    @ObsoleteCoroutinesApi
    private fun fetchMatches() {
        isNetworkError.value = false
        isNodata.value = false

        viewModelScope.launch {
            try {
                delay(3000) // simulate network delay
                for (event in tickerChannel) {
                    val scoresModel = scoresService.fetchScores()
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
                            localScore.add(Score(
                                it.id,
                                it.fts_A,
                                it.fts_B,
                                it.team_A.name,
                                it.team_B.name
                            ))
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