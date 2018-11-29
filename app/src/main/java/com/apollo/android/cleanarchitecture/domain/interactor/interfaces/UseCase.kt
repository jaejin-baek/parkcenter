package com.apollo.android.cleanarchitecture.domain.interactor.interfaces

interface UseCase<Request, Response> {
    fun execute(request: Request): Response
}