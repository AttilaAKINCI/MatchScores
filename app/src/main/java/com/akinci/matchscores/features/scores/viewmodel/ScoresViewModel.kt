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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@HiltViewModel
class ScoresViewModel @Inject constructor(
    private val coroutineContext: CoroutineContextProvider,
    private val scoresRepository: ScoresRepository
) : ViewModel() {
    // score table data
    private val _scores = MutableLiveData<Resource<List<Score>>>()
    val scores : LiveData<Resource<List<Score>>> = _scores

    private lateinit var tickerChannel : ReceiveChannel<Unit>
    override fun onCleared() {
        tickerChannel.cancel()
        super.onCleared()
    }

    init {
        Timber.d("ScoresViewModel created..")
    }

    fun stopTicker(){ tickerChannel.cancel() }
    fun initiateTicker(){
        val startDelay = if(_scores.value == null){ 0L } else { 30_000 }
        tickerChannel = ticker(delayMillis = 30_000, initialDelayMillis = startDelay)
        viewModelScope.launch(coroutineContext.IO) {
            try {
                for (event in tickerChannel){
                    fetchScores()
                }
            }catch (ex : Exception){
                Timber.d("Score detail fetch failed with exception ${ex.message}")
            }
        }
    }

    fun fetchScores() {
        // fetch scores once
        viewModelScope.launch(coroutineContext.IO) {
            Timber.tag("fetchScores-VMScope").d("Top-level: current thread is ${Thread.currentThread().name}")

            if(_scores.value == null){
                _scores.postValue(Resource.Loading())
                delay(1000)
            }

            when (val response = scoresRepository.fetchScores()) {
                is Resource.Success -> {
                    // fetch data and modify a little in order to manage better.
                    response.data?.let{
                        if(it.matches.isNotEmpty()){
                            val scoreList = mutableListOf<Score>()

                            //provide header data
                            val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("tr")).parse(it.date)
                            var formattedDate = ""
                            date?.let {
                                formattedDate = SimpleDateFormat("d MMMM y", Locale("tr")).format(date)
                            }

                            val title = "${it.name} - $formattedDate"
                            scoreList.add(Score(title))

                            it.matches.map { match ->
                                scoreList.add(
                                    Score(
                                        match.id,
                                        match.fts_A,
                                        match.fts_B,
                                        match.team_A.name,
                                        match.team_B.name
                                    )
                                )
                            }

                            _scores.postValue(Resource.Success(scoreList))
                            Timber.d("Score api service fetched ${it.matches.size} matches")

                        } else {
                            // there is not any score data
                            _scores.postValue(Resource.Info("There is not any score data"))
                        }
                    }
                }
                is Resource.Error -> {
                    // error occurred while fetching scores
                    _scores.postValue(Resource.Error(response.message))
                }
            }

        }
    }

}