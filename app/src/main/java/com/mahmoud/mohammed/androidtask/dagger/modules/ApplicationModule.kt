package com.mahmoud.mohammed.androidtask.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton


const val SCHEDULER_MAIN_THREAD = "mainThread"
const val SCHEDULER_IO = "io"

@Module
class ApplicationModule {
    @Provides
    @Named(SCHEDULER_MAIN_THREAD)
    fun provideAndroidMainThreadScheduler() : Scheduler = AndroidSchedulers.mainThread()
    @Provides
    @Named(SCHEDULER_IO)
    fun provideIoScheduler() : Scheduler = Schedulers.io()

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}