package com.apollo.android.cleanarchitecture.data.source.remote

import com.apollo.android.cleanarchitecture.domain.model.VideoFeedEntity
import io.reactivex.Observable

interface RemoteUserSource {
    fun getUsers(): Observable<List<VideoFeedEntity>>
}