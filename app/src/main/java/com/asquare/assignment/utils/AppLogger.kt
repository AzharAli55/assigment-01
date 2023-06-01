package com.asquare.assignment.utils

import android.util.Log
import com.asquare.assignment.BuildConfig


object AppLogger {

    const val TAG = "Assignment"
    fun logDebug(value: String?) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, value ?: "")
    }
}