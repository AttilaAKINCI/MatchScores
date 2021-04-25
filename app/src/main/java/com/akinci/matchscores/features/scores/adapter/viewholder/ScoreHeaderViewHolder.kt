package com.akinci.matchscores.features.scores.adapter.viewholder

import com.akinci.matchscores.common.component.listview.BaseViewHolder
import com.akinci.matchscores.databinding.RowScoreHeaderBinding
import com.akinci.matchscores.features.scores.data.output.Score

class ScoreHeaderViewHolder(val binding: RowScoreHeaderBinding) : BaseViewHolder<Score>(binding.root) {
    override fun bind(data: Score) {
        // fill score header row ..
        binding.data = data
        binding.executePendingBindings()
    }
}