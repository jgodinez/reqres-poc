package com.reqres.poc.ui

import androidx.multidex.MultiDexApplication
import com.reqres.poc.BuildConfig
import com.reqres.poc.bootstrap.TimberUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class ReqresApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        bootstrap()
    }

    private fun bootstrap() {
        val isDebug = BuildConfig.DEBUG
        TimberUtils.init(isDebug)
    }
}