package com.apollo.android.cleanarchitecture.presentation.main.viewer.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(containerView: View) : RecyclerView.ViewHolder(containerView) {
    abstract fun bind(item: T)

    open fun bind(item:T, payloads: List<Any>) {
        bind(item)
    }
}