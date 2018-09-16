package com.mahmoud.mohammed.androidtask.dagger.viewmodel

import androidx.lifecycle.ViewModel
import com.mahmoud.mohammed.androidtask.presentation.DeliveryListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass



@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DeliveryListViewModel::class)
    abstract fun bindCryptoListViewModel(viewModel: DeliveryListViewModel) : ViewModel
}