package com.akinci.matchscores.common.component.listview.viewholder

import com.akinci.matchscores.common.component.listview.BaseViewHolder
import com.akinci.matchscores.databinding.RowScoreShimmerBinding

class ScoreShimmerViewHolder(val binding: RowScoreShimmerBinding) : BaseViewHolder<Unit>(binding.root) {
    override fun bind(data: Unit) {
        binding.shimmerViewContainer.startShimmer()
    }
}