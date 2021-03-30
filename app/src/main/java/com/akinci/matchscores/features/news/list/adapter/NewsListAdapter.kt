package com.akinci.matchscores.features.news.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akinci.matchscores.common.component.setGlideImageCentered
import com.akinci.matchscores.databinding.RowNewsBinding
import com.akinci.matchscores.features.news.list.data.output.News

class NewsListAdapter(private val clickListener: (String) -> Unit) : ListAdapter<News, NewsListAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(RowNewsBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class NewsViewHolder(val binding: RowNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News, clickListener: (String) -> Unit) {
            // fill row instances..
            binding.newsCardView.setOnClickListener { clickListener.invoke(item.link) }
            binding.data = item

            binding.newsImage.setGlideImageCentered(item.picUrl)

            binding.executePendingBindings()
        }
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean { return oldItem.id == newItem.id }
        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean { return oldItem == newItem }
    }
}

