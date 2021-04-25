package com.akinci.matchscores.common.component.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akinci.matchscores.databinding.RowCardShimmerBinding
import com.akinci.matchscores.databinding.RowShimmerBinding

class ShimmerAdapter(private val randomItemCount: Int = (3..5).random()) : RecyclerView.Adapter<ShimmerAdapter.ShimmerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ShimmerViewHolder(RowCardShimmerBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int { return randomItemCount }
    override fun onBindViewHolder(holder: ShimmerViewHolder, position: Int) { holder.bind() }

    class ShimmerViewHolder(val binding: RowCardShimmerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() { binding.shimmerViewContainer.startShimmer() }
    }

}