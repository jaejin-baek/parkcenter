package com.apollo.android.cleanarchitecture

import android.app.Application
import com.apollo.android.cleanarchitecture.data.repository.UserServiceRepoImpl
import com.apollo.android.cleanarchitecture.data.source.remote.RemoteUserSource
import com.apollo.android.cleanarchitecture.data.source.remote.RemoteUserSourceImpl
import com.apollo.android.cleanarchitecture.domain.interactor.GetUsersUseCase
import com.apollo.android.cleanarchitecture.domain.repository.UserServiceRepo
import com.apollo.android.cleanarchitecture.presentation.main.MainViewModel
import com.apollo.android.cleanarchitecture.presentation.model.mapper.UserEntityMapper
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class MyApplication : Application() {
    private val dataModule = module {
        single<UserServiceRepo> { UserServiceRepoImpl() }
        single<RemoteUserSource> { RemoteUserSourceImpl() }
    }

    private val domainModule = module {
        single { GetUsersUseCase() }
        single { UserEntityMapper() }
    }

    private val presentationModule = module {
        viewModel { MainViewModel() }
    }

    override fun onCreate() {
        super.onCreate()

        // start koin
        startKoin(this, listOf(dataModule, domainModule, presentationModule))
    }
}