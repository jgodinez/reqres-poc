package com.reqres.users.data.di

import com.reqres.remote.domain.UseCase
import com.reqres.users.domain.GetUsersUseCase
import com.reqres.users.domain.UserEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DataModule {

    // Required due to Kotlin @Binds error with covariant type parameter
    // https://github.com/google/dagger/issues/1143#issuecomment-381776533
    @Binds
    fun bindsGetUsersUseCase(
        getUsersUseCase: GetUsersUseCase
    ): UseCase<GetUsersUseCase.Params, List<UserEntity>>

}