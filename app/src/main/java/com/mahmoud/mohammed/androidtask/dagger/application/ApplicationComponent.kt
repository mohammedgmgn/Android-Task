package com.mahmoud.mohammed.androidtask.dagger.application

import com.mahmoud.mohammed.androidtask.DeliveryApplication
import com.mahmoud.mohammed.androidtask.dagger.NetworkModule
import com.mahmoud.mohammed.androidtask.dagger.RepositoryModule
import com.mahmoud.mohammed.androidtask.dagger.UseCasesModule
import com.mahmoud.mohammed.androidtask.dagger.viewmodel.ViewModelFactoryModule
import com.mahmoud.mohammed.androidtask.dagger.viewmodel.ViewModelModule
import com.mahmoud.mohammed.androidtask.presentation.deliveries.dagger.DeliveriesActivityModule
import com.mahmoud.mohammed.androidtask.presentation.detail.dagger.DeliveryDetailsModule
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
        , DeliveryDetailsModule::class

))
interface ApplicationComponent {
    fun inject(app: DeliveryApplication)
}
