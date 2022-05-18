package com.reqres.remote

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.reqres.remote.qualifiers.ApiError
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    private object Timeout {
        const val CONNECT = 30L
        const val READ = 30L
        const val WRITE = 30L
        val UNIT = TimeUnit.MINUTES
    }

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun providesLoginInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
    }

    @ApiError
    @Provides
    fun providesErrorInterceptor(
        context: Context
    ): Interceptor {
        return Interceptor { chain ->
            try {
                chain.proceed(chain.request())
            } catch (exception: Throwable) {
                Timber.e(exception)
                val message: String = if (exception is UnknownHostException) {
                    context.getString(R.string.unknown_host_exception_error_message)
                } else {
                    exception.localizedMessage
                }
                throw IOException(message)
            }
        }
    }

    @Provides
    fun providesGsonFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun providesHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @ApiError errorInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(errorInterceptor)
            .connectTimeout(Timeout.CONNECT, Timeout.UNIT)
            .readTimeout(Timeout.READ, Timeout.UNIT)
            .writeTimeout(Timeout.WRITE, Timeout.UNIT)
            .build()
    }
}