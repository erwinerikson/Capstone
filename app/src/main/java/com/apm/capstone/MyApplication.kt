package com.apm.capstone

import android.app.Application
import com.apm.capstone.core.di.databaseModule
import com.apm.capstone.core.di.networkModule
import com.apm.capstone.core.di.repositoryModule
import com.apm.capstone.di.useCaseModule
import com.apm.capstone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}