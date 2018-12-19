package com.apollo.android.cleanarchitecture.data.source.remote

import com.apollo.android.cleanarchitecture.domain.model.VideoFeedEntity
import io.reactivex.Observable

class RemoteUserSourceImpl:
    RemoteUserSource {
    override fun getUsers(): Observable<List<VideoFeedEntity>> {
        // Mock 연동

        val users = arrayListOf<VideoFeedEntity>()

        for(i in 0 .. 5) {
            users.add(
                VideoFeedEntity(
                    i.toLong(),
                    "jj-$i"
                )
            )
        }


        return Observable.just(users)
    }
}