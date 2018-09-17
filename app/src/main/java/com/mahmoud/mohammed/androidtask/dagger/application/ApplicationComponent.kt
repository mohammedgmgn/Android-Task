package com.mahmoud.mohammed.androidtask.dagger.application

import com.mahmoud.mohammed.androidtask.MyApplication
import com.mahmoud.mohammed.androidtask.dagger.*
import com.mahmoud.mohammed.androidtask.dagger.viewmodel.ViewModelFactoryModule
import com.mahmoud.mohammed.androidtask.dagger.viewmodel.ViewModelModule
import com.mahmoud.mohammed.androidtask.presentation.activities.DeliveriesActivityModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UseCasesModule::class,
        ViewModelModule::class,
        DeliveriesActivityModule::class
        ))
interface ApplicationComponent {
    fun inject(app: MyApplication)
}
