package com.akinci.matchscores.common.component.listview.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.akinci.matchscores.R
import com.akinci.matchscores.common.component.listview.BaseViewHolder
import com.akinci.matchscores.databinding.RowCardShimmerBinding
import com.akinci.matchscores.databinding.RowScoreShimmerBinding

class ShimmerViewHolderTypeFactory {
    companion object {
        const val NEWS_SHIMMER_VIEW = R.layout.row_card_shimmer
        const val SCORE_SHIMMER_VIEW = R.layout.row_score_shimmer
    }

    fun create(layoutInflater:LayoutInflater, parent: ViewGroup, viewType: Int): BaseViewHolder<Unit> {
        return when(viewType) {
            NEWS_SHIMMER_VIEW -> NewsShimmerViewHolder(RowCardShimmerBinding.inflate(layoutInflater, parent, false))
            else -> ScoreShimmerViewHolder(RowScoreShimmerBinding.inflate(layoutInflater, parent, false))
        }
    }
}