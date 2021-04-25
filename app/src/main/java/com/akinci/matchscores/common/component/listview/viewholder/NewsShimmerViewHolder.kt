package com.akinci.matchscores.common.component.listview.viewholder

import com.akinci.matchscores.common.component.listview.BaseViewHolder
import com.akinci.matchscores.databinding.RowCardShimmerBinding

class NewsShimmerViewHolder(val binding: RowCardShimmerBinding) : BaseViewHolder<Unit>(binding.root) {
    override fun bind(data: Unit) {
        binding.shimmerViewContainer.startShimmer()
    }
}