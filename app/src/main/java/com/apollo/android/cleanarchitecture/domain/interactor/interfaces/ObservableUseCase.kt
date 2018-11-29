package com.apollo.android.cleanarchitecture.domain.interactor.interfaces

import io.reactivex.Observable

interface ObservableUseCase<Request, Response>:
    UseCase<Request, Observable<Response>>