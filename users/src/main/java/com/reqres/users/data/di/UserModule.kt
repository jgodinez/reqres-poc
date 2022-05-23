package com.reqres.users.data.di

import com.reqres.users.data.UserRemoteDataSource
import com.reqres.users.data.UserRemoteDataSourceImpl
import com.reqres.users.data.UserRepositoryImpl
import com.reqres.users.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UserModule {

    @Binds
    abstract fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindsUserRemoteDataSource(
        userRemoteDataSourceImpl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource

}