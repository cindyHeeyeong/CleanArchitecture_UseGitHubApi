package com.example.cleanarchitecture_toyproject

import android.app.Application
import com.example.cleanarchitecture_toyproject.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger


class AndroidApplication : Application() {

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
           // startKoin(this, listOf(appModule))
            androidContext(this@AndroidApplication)
            modules(myModule)
            EmptyLogger()
        }

    }

    companion object {
        lateinit var INSTANCE : AndroidApplication
    }

}
