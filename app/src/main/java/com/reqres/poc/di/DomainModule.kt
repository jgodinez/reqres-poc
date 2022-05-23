package com.reqres.poc.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DomainModule {
    @Provides
    fun providesCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}