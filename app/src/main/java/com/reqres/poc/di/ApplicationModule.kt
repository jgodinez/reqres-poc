package com.reqres.poc.di

import android.content.Context
import com.reqres.component.ImageLoader
import com.reqres.poc.BuildConfig
import com.reqres.poc.ui.component.PicassoImageLoader
import com.reqres.remote.di.qualifiers.enpoint.ReqresApiUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @ReqresApiUrl
    @Provides
    fun providesReqresApiUrl(): String {
        return BuildConfig.REQRES_API_URL
    }

    @Provides
    fun providesImageLoader(@ApplicationContext context: Context): ImageLoader =
        PicassoImageLoader(context, BuildConfig.DEBUG)
}