package com.matchscores.commons.component.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matchscores.databinding.RowCardShimmerBinding
import com.matchscores.databinding.RowScoreShimmerBinding

class ShimmerAdapter(private val type : ShimmerType) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val randomItemCount = (2..4).random() // 2,3 or 4 instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(type){
            ShimmerType.CARD -> {
                CardShimmerViewHolder(RowCardShimmerBinding.inflate(inflater, parent, false))
            }
            ShimmerType.SCORE -> {
                ScoreShimmerViewHolder(RowScoreShimmerBinding.inflate(inflater, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return if(type == ShimmerType.CARD){
            randomItemCount
        } else {
            1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if(holder is CustomViewHolder<*>) { holder.bind() }
    }

    // Score Type Shimmer
    class ScoreShimmerViewHolder(val binding: RowScoreShimmerBinding) : CustomViewHolder<Any>(binding.root) {
        override fun bind(){ binding.shimmerViewContainer.startShimmer() }
    }
    // Card Type Shimmer
    class CardShimmerViewHolder(val binding: RowCardShimmerBinding) : CustomViewHolder<Any>(binding.root) {
        override fun bind() { binding.shimmerViewContainer.startShimmer() }
    }

}