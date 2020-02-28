package com.asb.presentation

import android.app.Application
import com.asb.core.network.networkModule
import com.asb.core.repository.loginModule
import com.asb.core.repository.patientModule
import com.asb.core.utils.prefModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    prefModule,
                    networkModule,
                    loginModule,
                    patientModule,
                    viewModelModule
                )
            )
        }
    }
}