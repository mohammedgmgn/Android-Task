package com.mahmoud.mohammed.androidtask.dagger.application

import com.antonicastejon.cryptodata.CryptoApplication
import com.antonicastejon.cryptodata.presentation.main.di.MainActivityModule
import com.mahmoud.mohammed.androidtask.dagger.*
import com.mahmoud.mohammed.androidtask.dagger.viewmodel.ViewModelFactoryModule
import com.mahmoud.mohammed.androidtask.dagger.viewmodel.ViewModelModule
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
        MainActivityModule::class
        ))
interface ApplicationComponent {
    fun inject(app: CryptoApplication)
}