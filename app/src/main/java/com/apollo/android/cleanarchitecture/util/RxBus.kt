package com.apollo.android.cleanarchitecture.util

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

object RxBus {
    private val publisher = PublishRelay.create<Any>()

    fun publish(event: Any) {
        publisher.accept(event)
    }

    inline fun <reified T> listen(): Observable<T> = listen(T::class.java)

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}