package com.apollo.android.cleanarchitecture.data.repository

import com.apollo.android.cleanarchitecture.data.source.remote.RemoteUserSource
import com.apollo.android.cleanarchitecture.domain.model.UserEntity
import com.apollo.android.cleanarchitecture.domain.repository.UserServiceRepo
import io.reactivex.Observable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class UserServiceRepoImpl : UserServiceRepo, KoinComponent {
    private val remoteDataSource: RemoteUserSource by inject()


    override fun getUsers(): Observable<List<UserEntity>> {
        return remoteDataSource.getUsers()
    }
}