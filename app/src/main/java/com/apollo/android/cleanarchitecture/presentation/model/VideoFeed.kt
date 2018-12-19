package com.apollo.android.cleanarchitecture.presentation.model

import android.graphics.Bitmap

data class VideoFeed(
    val id: Long,
    val name: String,
    val url: String = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",
    var width: Int = 0,
    var height: Int = 0,
    var currentPosition: Long = 0,
    var thumb: Bitmap? = null,
    val imageUrl: String = "http://a2.mzstatic.com/us/r30/Purple/v4/a5/d7/a1/a5d7a17b-1f36-e40f-63cb-bb0eb220cc59/screen320x480.jpeg"
)