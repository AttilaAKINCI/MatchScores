package com.akinci.matchscores.features.scores.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akinci.matchscores.databinding.RowScoreBinding
import com.akinci.matchscores.databinding.RowScoreHeaderBinding
import com.akinci.matchscores.features.scores.data.output.Score

class ScoresListAdapter : ListAdapter<Score, RecyclerView.ViewHolder>(ScoresDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            ScoreType.HEADER.ordinal -> {
                ScoreHeaderViewHolder(RowScoreHeaderBinding.inflate(layoutInflater, parent, false))
            } else -> {
                ScoreViewHolder(RowScoreBinding.inflate(layoutInflater, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if(holder is ScoreViewHolder) { holder.bind(item, position) }
        if(holder is ScoreHeaderViewHolder) { holder.bind(item) }
    }

    override fun getItemViewType(position: Int): Int { return getItem(position).type }

    class ScoreViewHolder(val binding: RowScoreBinding) :  CustomViewHolder<Score>(binding.root) {
        override fun bind(item: Score, position: Int) {
             // fill row instances..
            binding.data = item
            binding.executePendingBindings()
        }
    }

    class ScoreHeaderViewHolder(val binding: RowScoreHeaderBinding) :  CustomViewHolder<Score>(binding.root) {
        override fun bind(item: Score) {
            // fill score header row ..
            binding.data = item
            binding.executePendingBindings()
        }
    }

    class ScoresDiffCallback : DiffUtil.ItemCallback<Score>() {
        override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
            if(newItem.type == ScoreType.SCORE.ordinal){ return oldItem.id == newItem.id }
            return true
        }

        override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
            return oldItem == newItem
        }
    }
}