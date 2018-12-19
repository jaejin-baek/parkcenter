package com.apollo.android.cleanarchitecture.domain.interactor

import com.apollo.android.cleanarchitecture.domain.interactor.interfaces.ObservableUseCase
import com.apollo.android.cleanarchitecture.domain.model.VideoFeedEntity
import com.apollo.android.cleanarchitecture.domain.repository.UserServiceRepo
import io.reactivex.Observable
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class GetUsersUseCase : ObservableUseCase<GetUsersUseCase.Param, List<VideoFeedEntity>>, KoinComponent {

    private val userServiceRepo: UserServiceRepo by inject()

    override fun execute(request: Param): Observable<List<VideoFeedEntity>> {
        return userServiceRepo.getUsers()
    }

    data class Param(val userId: Long)
}