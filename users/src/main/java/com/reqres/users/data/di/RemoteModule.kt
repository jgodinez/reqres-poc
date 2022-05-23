package com.reqres.users.data.di

import com.reqres.remote.di.qualifiers.enpoint.ReqresApiUrl
import com.reqres.users.data.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    @Provides
    fun providesUserService(
        @ReqresApiUrl baseUrl: String,
        client: OkHttpClient,
        factory: Converter.Factory
    ): UserService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(UserService::class.java)
    }

}