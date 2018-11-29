package com.mahmoud.mohammed.androidtask.dagger.application

import android.app.Application
import com.mahmoud.mohammed.androidtask.DeliveryApplication
import com.mahmoud.mohammed.androidtask.dagger.ActivityBuilder
import com.mahmoud.mohammed.androidtask.dagger.modules.ApplicationModule
import com.mahmoud.mohammed.androidtask.dagger.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ActivityBuilder::class,
    AndroidSupportInjectionModule::class
    , ApplicationModule::class, NetworkModule::class])

interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: DeliveryApplication)

}
