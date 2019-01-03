package com.apollo.android.cleanarchitecture.event

import android.view.View
import com.apollo.android.cleanarchitecture.util.RxBus

class VideoOnClickEvent constructor(val feedId: Long, val view: View) {

    companion object {
        fun publish(feedId: Long, view: View) {
            RxBus.publish(VideoOnClickEvent(feedId, view))
        }
    }
}