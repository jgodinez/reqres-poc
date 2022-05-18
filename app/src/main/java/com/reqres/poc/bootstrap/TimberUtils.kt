package com.reqres.poc.bootstrap

import android.util.Log
import timber.log.Timber

object TimberUtils {
    fun init(isDebug: Boolean) {
        val reportingTree = if (isDebug) {
            object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String =
                    super.createStackElementTag(element) + ':' + element.lineNumber
            }
        } else CrashReportingTree()

        Timber.plant(reportingTree)
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, error: Throwable?) {
            when (priority) {
                Log.VERBOSE, Log.DEBUG, Log.INFO -> return
                else -> {
                    recordException(priority, message, error)
                }
            }
        }

        private fun recordException(priority: Int, message: String, error: Throwable?) {
            // crashlytics error handling
            val tag = "crashlytics"
            if (error != null) {
                Log.println(priority, tag, error.localizedMessage)
            } else {
                Log.println(priority, tag, message)
            }
        }
    }
}