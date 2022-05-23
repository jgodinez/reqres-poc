package com.reqres.users.data

import com.reqres.remote.data.ListWrapper
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UserService {
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int? = null,
        @Query("per_page") perPage: Int? = null
    ): ListWrapper<UserDto>
}