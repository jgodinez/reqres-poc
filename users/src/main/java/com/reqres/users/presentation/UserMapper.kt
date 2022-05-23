package com.reqres.users.presentation

import com.reqres.users.domain.UserEntity

internal fun UserEntity.toUserModel() = UserModel(
    avatar = avatar,
    email = email,
    firstName = firstName,
    id = id,
    lastName = lastName
)

internal fun List<UserEntity>.toUserModelEntities() = map { it.toUserModel() }