package com.akinci.matchscores.features.scores.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akinci.matchscores.databinding.RowScoreBinding
import com.akinci.matchscores.features.scores.data.output.Score

class ScoresListAdapter : ListAdapter<Score, RecyclerView.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ScoreViewHolder(RowScoreBinding.inflate(layoutInflater, parent, false))
        //when(viewType){
//            ScoreType.HEADER.ordinal -> {
//                ScoreHeaderViewHolder(RowScoreHeaderBinding.inflate(layoutInflater, parent, false))
//            } else -> {

         //   }
   //     }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if(holder is ScoreViewHolder) { holder.bind(item, position) }
       // if(holder is ScoreHeaderViewHolder) { holder.bind(item) }
    }

    override fun getItemViewType(position: Int): Int { return getItem(position).type }

    class ScoreViewHolder(val binding: RowScoreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Score, position: Int) {
             // fill row instances..
            binding.data = item
            binding.executePendingBindings()
        }
    }

//    class ScoreHeaderViewHolder(val binding: RowScoreHeaderBinding) :  CustomViewHolder<Score>(binding.root) {
//        override fun bind(item: Score) {
//            // fill score header row ..
//            binding.data = item
//            binding.executePendingBindings()
//        }
//    }

}

class DiffCallBack : DiffUtil.ItemCallback<Score>() {
    override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
        return oldItem == newItem
    }
}
