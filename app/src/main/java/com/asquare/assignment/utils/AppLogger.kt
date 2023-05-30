package com.asquare.assignment.utils

import android.util.Log


object AppLogger {

    fun logDebug(value:String?){
        Log.d("Assignment",value?:"")
    }
}