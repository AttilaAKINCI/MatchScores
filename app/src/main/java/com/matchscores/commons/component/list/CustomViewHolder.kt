package com.matchscores.commons.component.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class CustomViewHolder<T>(view : View) : RecyclerView.ViewHolder(view) {
    open fun bind() {}
    open fun bind(item : T) {}
    open fun bind(item : T, position: Int) {}
}