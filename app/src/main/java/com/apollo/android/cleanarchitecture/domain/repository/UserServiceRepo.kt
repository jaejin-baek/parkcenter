package com.apollo.android.cleanarchitecture.domain.repository

import com.apollo.android.cleanarchitecture.domain.model.VideoFeedEntity
import io.reactivex.Observable

interface UserServiceRepo {
    fun getUsers(): Observable<List<VideoFeedEntity>>
}