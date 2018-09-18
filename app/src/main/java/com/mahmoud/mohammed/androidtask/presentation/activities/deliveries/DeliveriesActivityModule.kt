package com.mahmoud.mohammed.androidtask.presentation.activities.deliveries

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap



@Module(subcomponents = arrayOf(DeliveriesActivitySubcomponent::class))
abstract class DeliveriesActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(DeliveriesActivity::class)
    abstract fun bindDeliveriesActivityInjectorFactory(builder: DeliveriesActivitySubcomponent.Builder)
            : AndroidInjector.Factory<out Activity>
}