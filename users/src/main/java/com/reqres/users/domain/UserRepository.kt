package com.reqres.users.domain

interface UserRepository {
    suspend fun loadUsers(page: Int, perPage: Int): List<UserEntity>
}