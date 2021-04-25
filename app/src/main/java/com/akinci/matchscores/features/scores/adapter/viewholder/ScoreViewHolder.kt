package com.akinci.matchscores.features.scores.adapter.viewholder

import com.akinci.matchscores.common.component.listview.BaseViewHolder
import com.akinci.matchscores.databinding.RowScoreBinding
import com.akinci.matchscores.features.scores.data.output.Score

class ScoreViewHolder(val binding: RowScoreBinding) : BaseViewHolder<Score>(binding.root) {
    override fun bind(data: Score) {
        // fill row instances..
        binding.data = data
        binding.executePendingBindings()
    }
}