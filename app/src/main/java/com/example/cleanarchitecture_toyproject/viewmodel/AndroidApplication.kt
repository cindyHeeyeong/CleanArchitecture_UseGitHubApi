package com.example.cleanarchitecture_toyproject.viewmodel

import android.app.Application
import android.content.Context

class AndroidApplication : Application() {
   /* override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        var instance: AndroidApplication? = null
            private set

        operator fun get(context: Context) : AndroidApplication {
            return context.applicationContext as AndroidApplication
        }
    }*/

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        lateinit var INSTANCE : AndroidApplication
    }
}
