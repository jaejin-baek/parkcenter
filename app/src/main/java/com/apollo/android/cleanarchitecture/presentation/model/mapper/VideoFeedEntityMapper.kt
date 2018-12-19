package com.apollo.android.cleanarchitecture.presentation.model.mapper

import com.apollo.android.cleanarchitecture.domain.model.VideoFeedEntity
import com.apollo.android.cleanarchitecture.domain.model.mapper.Mapper
import com.apollo.android.cleanarchitecture.presentation.model.VideoFeed

class VideoFeedEntityMapper :
    Mapper<VideoFeedEntity, VideoFeed> {
    override fun map(from: VideoFeedEntity): VideoFeed {
        with(from) {
            return VideoFeed(id, name, url, width, height, currentPosition, thumb, imageUrl)
        }
    }
}