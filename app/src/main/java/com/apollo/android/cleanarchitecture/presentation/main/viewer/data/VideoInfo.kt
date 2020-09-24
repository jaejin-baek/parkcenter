package com.apollo.android.cleanarchitecture.presentation.main.viewer.data

sealed class VideoItem(val type: Type) {
    enum class Type {
        VIDEO_INFO,
        CHANNEL_INFO
    }

    abstract val id: String
}

data class VideoInfo(
        val userId: Int,
        val userName: String,
        val videoFeed: VideoFeed
) : VideoItem(Type.VIDEO_INFO) {
    override val id: String
        get() = Type.VIDEO_INFO.name
}