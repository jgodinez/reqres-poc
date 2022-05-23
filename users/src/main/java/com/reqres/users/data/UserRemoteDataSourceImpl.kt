package com.reqres.users.data

import javax.inject.Inject

internal class UserRemoteDataSourceImpl @Inject constructor(
    private val service: UserService
) : UserRemoteDataSource {
    override suspend fun loadUsers(page: Int, perPage: Int): List<UserDto> {
        return service.getUsers(page, perPage).data
    }
}