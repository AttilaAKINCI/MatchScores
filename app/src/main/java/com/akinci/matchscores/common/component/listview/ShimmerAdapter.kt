package com.akinci.matchscores.common.component.listview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akinci.matchscores.common.component.listview.viewholder.ShimmerViewHolderTypeFactory

class ShimmerAdapter(
    val type : Int,
    private val randomItemCount: Int = (3..5).random()
) : RecyclerView.Adapter<BaseViewHolder<Unit>>() {

    private val typeFactory = ShimmerViewHolderTypeFactory()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Unit> {
        val inflater = LayoutInflater.from(parent.context)
        return typeFactory.create(inflater, parent, viewType)
    }

    override fun getItemViewType(position: Int): Int { return type }
    override fun onBindViewHolder(holder: BaseViewHolder<Unit>, position: Int) { holder.bind(Unit) }

    override fun getItemCount(): Int { return randomItemCount }
}