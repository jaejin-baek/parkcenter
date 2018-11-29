package com.apollo.android.cleanarchitecture.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apollo.android.cleanarchitecture.domain.interactor.GetUsersUseCase
import com.apollo.android.cleanarchitecture.presentation.model.User
import com.apollo.android.cleanarchitecture.presentation.model.mapper.UserEntityMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.SerialDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.get

class MainViewModel : ViewModel(), KoinComponent {

    private val userId: Long = 3

    private val _users = MutableLiveData<List<User>>()

    val users: LiveData<List<User>>
        get() = _users

    private var disposable: Disposable = SerialDisposable()

    fun loadData():Int {
        val getUsersUseCase: GetUsersUseCase = get()
        val userMapper: UserEntityMapper = get()

        disposable = getUsersUseCase.execute(GetUsersUseCase.Param(userId))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                userMapper.map(it)
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