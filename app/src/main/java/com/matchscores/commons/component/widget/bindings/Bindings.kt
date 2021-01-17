package com.matchscores.commons.component.widget.bindings

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.matchscores.commons.component.widget.ScoreBoard
import com.matchscores.features.scores.model.Score

@BindingAdapter("scoreBoardData")
fun insertData (scoreBoard: ScoreBoard, data : MutableLiveData<List<Score>>){
    scoreBoard.insertScoreBoardData(data)
}