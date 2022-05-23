package com.reqres.users.data

import com.reqres.users.domain.UserEntity
import com.reqres.users.domain.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {
    override suspend fun loadUsers(page: Int, perPage: Int): List<UserEntity> {
        return remoteDataSource.loadUsers(page, perPage).toUserEntities()
    }
}