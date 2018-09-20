package com.mahmoud.mohammed.androidtask.presentation.detail.dagger

import android.app.Activity
import com.mahmoud.mohammed.androidtask.presentation.deliveries.activities.DeliveriesActivity
import com.mahmoud.mohammed.androidtask.presentation.deliveries.dagger.DeliveriesActivitySubcomponent
import com.mahmoud.mohammed.androidtask.presentation.detail.activities.DeliveryDetailsActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(DeliveryDetailsActivitySubcomponent::class))

abstract class DeliveryDetailsModule {
    @Binds
    @IntoMap
    @ActivityKey(DeliveryDetailsActivity::class)
    abstract fun bindDeliveriesActivityInjectorFactory(builder: DeliveryDetailsActivitySubcomponent.Builder)
            : AndroidInjector.Factory<out Activity>


}