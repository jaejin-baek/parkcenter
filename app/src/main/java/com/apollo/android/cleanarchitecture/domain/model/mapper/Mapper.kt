package com.apollo.android.cleanarchitecture.domain.model.mapper

interface Mapper<From, To> {
    fun map(from: From): To

    fun map(from: List<From>): List<To> = from.map { map(it) }
}