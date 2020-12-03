package com.example.nativeapps

import android.app.Application
import com.example.nativeapps.DI.networkModule
import com.example.nativeapps.DI.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                networkModule,
                viewModelModule
            )
        }
    }
}
