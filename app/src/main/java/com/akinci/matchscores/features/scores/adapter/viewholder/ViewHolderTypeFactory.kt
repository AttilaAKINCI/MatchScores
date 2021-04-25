package com.akinci.matchscores.features.scores.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.akinci.matchscores.common.component.listview.BaseViewHolder
import com.akinci.matchscores.databinding.RowScoreBinding
import com.akinci.matchscores.databinding.RowScoreHeaderBinding
import com.akinci.matchscores.features.scores.data.output.Score
import com.akinci.matchscores.features.scores.data.output.Score.Companion.HEADER_VIEW
import com.akinci.matchscores.features.scores.data.output.Score.Companion.SCORE_VIEW

class ViewHolderTypeFactory {
    fun create(layoutInflater:LayoutInflater, parent: ViewGroup, viewType: Int): BaseViewHolder<Score> {
        return when(viewType) {
            HEADER_VIEW -> ScoreHeaderViewHolder(RowScoreHeaderBinding.inflate(layoutInflater, parent, false))
            else -> ScoreViewHolder(RowScoreBinding.inflate(layoutInflater, parent, false))
        }
    }

    fun type(item: Score) =
        if(item.type == 0){
            HEADER_VIEW
        }else{
            SCORE_VIEW
        }
}