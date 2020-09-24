package com.apollo.android.cleanarchitecture.presentation.main.viewer.holder

import android.content.Context
import android.view.View
import com.apollo.android.cleanarchitecture.presentation.main.viewer.data.VideoItem

class EmptyHolder(context: Context): BaseViewHolder<VideoItem>(View(context)) {
    override fun bind(item: VideoItem) {}
}