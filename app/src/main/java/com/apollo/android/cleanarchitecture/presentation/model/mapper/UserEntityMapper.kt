package com.apollo.android.cleanarchitecture.presentation.model.mapper

import com.apollo.android.cleanarchitecture.domain.model.mapper.Mapper
import com.apollo.android.cleanarchitecture.domain.model.UserEntity
import com.apollo.android.cleanarchitecture.presentation.model.User

class UserEntityMapper :
    Mapper<UserEntity, User> {
    override fun map(from: UserEntity): User {
        return User(from.id, from.name)
    }
}