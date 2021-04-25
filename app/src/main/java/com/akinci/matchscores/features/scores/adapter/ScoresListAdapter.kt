package com.akinci.matchscores.features.scores.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.akinci.matchscores.common.component.listview.BaseViewHolder
import com.akinci.matchscores.features.scores.adapter.viewholder.ViewHolderTypeFactory
import com.akinci.matchscores.features.scores.data.output.Score

class ScoresListAdapter : ListAdapter<Score, BaseViewHolder<Score>>(DiffCallBack()) {

    private val typeFactory = ViewHolderTypeFactory()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Score> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return typeFactory.create(layoutInflater, parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type(typeFactory)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Score>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class DiffCallBack : DiffUtil.ItemCallback<Score>() {
    override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
        return oldItem == newItem
    }
}
