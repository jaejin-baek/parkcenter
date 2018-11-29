package com.apollo.android.cleanarchitecture.data.source.remote

import com.apollo.android.cleanarchitecture.domain.model.UserEntity
import io.reactivex.Observable

interface RemoteUserSource {
    fun getUsers(): Observable<List<UserEntity>>
}