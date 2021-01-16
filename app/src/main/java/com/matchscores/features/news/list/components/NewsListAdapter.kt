package com.matchscores.features.news.list.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.matchscores.commons.component.base.GlideApp
import com.matchscores.databinding.RowNewsBinding
import com.matchscores.features.news.list.model.News

class NewsListAdapter(private val clickListener: (String) -> Unit) : ListAdapter<News, RecyclerView.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(RowNewsBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if(holder is NewsViewHolder) { holder.bind(item, clickListener) }
    }

    class NewsViewHolder(val binding: RowNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News, clickListener: (String) -> Unit) {
            // fill row instances..
            binding.newsCardView.setOnClickListener { clickListener.invoke(item.link) }
            binding.data = item

            GlideApp.with(binding.newsImage.context)
                .load(item.picUrl)
                .centerCrop()
                .into(binding.newsImage)

            binding.executePendingBindings()
        }
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }
}

