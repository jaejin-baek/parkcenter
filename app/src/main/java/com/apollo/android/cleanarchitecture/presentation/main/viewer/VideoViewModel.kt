package com.apollo.android.cleanarchitecture.presentation.main.viewer

import com.apollo.android.cleanarchitecture.domain.model.VideoFeedEntity
import com.apollo.android.cleanarchitecture.presentation.main.viewer.data.VideoFeed
import com.apollo.android.cleanarchitecture.presentation.main.viewer.data.VideoInfo
import com.apollo.android.cleanarchitecture.presentation.main.viewer.data.VideoItem

class VideoViewModel {

    fun getVideoData(): List<VideoItem> {
        val videoItems = mutableListOf<VideoItem>()

        for (pos in 1..100) {
            val videoFeed = VideoFeed(pos.toLong(), "jaejin-$pos")

            val videoItem = VideoInfo(pos, "jaejin-$pos", videoFeed)

            videoItems.add(videoItem)
        }

        return videoItems
    }
}