package com.apollo.android.cleanarchitecture.presentation.main.viewer.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apollo.android.cleanarchitecture.R
import com.apollo.android.cleanarchitecture.presentation.main.viewer.data.VideoFeed
import com.apollo.android.cleanarchitecture.presentation.main.viewer.data.VideoInfo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*

class VideoInfoHolder(override val containerView: View) : BaseViewHolder<VideoInfo>(containerView), LayoutContainer {

    private var currentVideoFeed: VideoFeed? = null

    companion object {
        fun from(parent: ViewGroup): VideoInfoHolder {
            return VideoInfoHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.item_user,
                            parent,
                            false
                    )
            )
        }
    }

    override fun bind(item: VideoInfo) {
        currentVideoFeed = item.videoFeed

        guestUserId.text = item.videoFeed.id.toString()
        userName.text = item.videoFeed.name
    }
}