package com.apollo.android.cleanarchitecture.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apollo.android.cleanarchitecture.domain.interactor.GetUsersUseCase
import com.apollo.android.cleanarchitecture.presentation.model.VideoFeed
import com.apollo.android.cleanarchitecture.presentation.model.mapper.VideoFeedEntityMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.SerialDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.get

class MainViewModel : ViewModel(), KoinComponent {

    private val userId: Long = 3

    private val _users = MutableLiveData<List<VideoFeed>>()

    val users: LiveData<List<VideoFeed>>
        get() = _users

    private var disposable: Disposable = SerialDisposable()

    fun loadData():Int {
        val getUsersUseCase: GetUsersUseCase = get()
        val videoFeedMapper: VideoFeedEntityMapper = get()

        disposable = getUsersUseCase.execute(GetUsersUseCase.Param(userId))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                videoFeedMapper.map(it)
            }.subscribe {
                _users.value = it

                Log.d("@jj", "[ViewModel] subscribe result : ${it}")
            }
        return 0
    }

    override fun onCleared() {
        super.onCleared()

        if (disposable.isDisposed.not()) {
            disposable.dispose()
        }
    }
}