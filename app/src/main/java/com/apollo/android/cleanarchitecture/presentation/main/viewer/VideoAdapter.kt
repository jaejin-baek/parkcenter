package com.apollo.android.cleanarchitecture.presentation.main.viewer

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apollo.android.cleanarchitecture.presentation.main.viewer.data.VideoInfo
import com.apollo.android.cleanarchitecture.presentation.main.viewer.data.VideoItem
import com.apollo.android.cleanarchitecture.presentation.main.viewer.holder.BaseViewHolder
import com.apollo.android.cleanarchitecture.presentation.main.viewer.holder.EmptyHolder
import com.apollo.android.cleanarchitecture.presentation.main.viewer.holder.VideoInfoHolder

class VideoAdapter : ListAdapter<VideoItem, BaseViewHolder<out VideoItem>>(VideoDiffCallback()) {
    companion object {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<out VideoItem> {
        return when (viewType) {
            VideoItem.Type.VIDEO_INFO.ordinal -> VideoInfoHolder.from(parent)

            else -> EmptyHolder(parent.context)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<out VideoItem>, position: Int) {
        val item = getItem(position)

        when (holder) {
            is VideoInfoHolder -> holder.bind(item as VideoInfo)
        }
    }

    class VideoDiffCallback : DiffUtil.ItemCallback<VideoItem>() {

        override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
            return oldItem.type == newItem.type
        }

        override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
            if (oldItem is VideoInfo && newItem is VideoInfo) {
                return (oldItem.videoFeed.id == newItem.videoFeed.id)
            }

            return oldItem == newItem
        }
    }
}