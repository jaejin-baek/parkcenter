package com.apollo.android.cleanarchitecture.data.source.remote

import com.apollo.android.cleanarchitecture.domain.model.UserEntity
import io.reactivex.Observable

class RemoteUserSourceImpl:
    RemoteUserSource {
    override fun getUsers(): Observable<List<UserEntity>> {
        // Mock 연동

        val users = arrayListOf<UserEntity>()

        for(i in 0 .. 100) {
            users.add(
                UserEntity(
                    i.toLong(),
                    "jj-$i"
                )
            )
        }


        return Observable.just(users)
    }
}