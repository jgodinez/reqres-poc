package com.reqres.users.data

import com.reqres.users.domain.UserEntity

internal fun UserDto.toEntity(): UserEntity {
    return UserEntity(
        avatar = avatar,
        email = email,
        firstName = firstName,
        id = id,
        lastName = lastName
    )
}

internal fun List<UserDto>.toUserEntities() = map { it.toEntity() }