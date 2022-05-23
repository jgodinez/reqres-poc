package com.reqres.users.data

internal interface UserRemoteDataSource {
    suspend fun loadUsers(page: Int, perPage: Int): List<UserDto>
}