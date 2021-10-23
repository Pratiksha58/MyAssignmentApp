package com.app.assignmentapp.presentation.utils

import android.app.Application
import com.facebook.stetho.Stetho
import com.app.assignmentapp.presentation.di.PostModule
import com.app.assignmentapp.presentation.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UserApplication : Application(){

    companion object {
        private lateinit var instance: UserApplication
        fun getInstance(): UserApplication = instance


    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        instance = this
        startKoin {
            androidLogger()
            androidContext(this@UserApplication)
            modules(
                listOf(
                    PostModule,
                    networkModule
                )
            )
        }
    }
}