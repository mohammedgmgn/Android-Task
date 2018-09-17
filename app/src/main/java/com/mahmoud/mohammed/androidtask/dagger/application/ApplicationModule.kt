package com.mahmoud.mohammed.androidtask.dagger.application

import android.content.Context
import com.mahmoud.mohammed.androidtask.common.imagehelper.ImageLoader
import com.mahmoud.mohammed.androidtask.common.imagehelper.PicassoImageLoader
import com.squareup.picasso.Picasso
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
class ApplicationModule constructor(context: Context) {
    private val appContext = context.applicationContext

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @Provides
    @Named(SCHEDULER_MAIN_THREAD)
    fun provideAndroidMainThreadScheduler() : Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named(SCHEDULER_IO)
    fun provideIoScheduler() : Scheduler = Schedulers.io()

    @Singleton
    @Provides
    fun provideImageLoader(context: Context) : ImageLoader {
        return PicassoImageLoader(Picasso.with(context))
    }

}