package com.apollo.android.cleanarchitecture.presentation.main.viewer.data

import android.graphics.Bitmap
import com.apollo.android.cleanarchitecture.util.PLAY_URL
import com.apollo.android.cleanarchitecture.util.THUMBNAIL_URL

data class VideoFeed(
        val id: Long,
        val name: String,
        val url: String = PLAY_URL,
        var width: Int = 0,
        var height: Int = 0,
        var currentPosition: Long = 0,
        var thumb: Bitmap? = null,
        val imageUrl: String = THUMBNAIL_URL
)