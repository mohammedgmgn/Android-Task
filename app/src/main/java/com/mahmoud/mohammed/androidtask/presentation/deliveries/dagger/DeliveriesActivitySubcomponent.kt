package com.mahmoud.mohammed.androidtask.presentation.deliveries.dagger

import com.mahmoud.mohammed.androidtask.presentation.deliveries.activities.DeliveriesActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector



@Subcomponent(modules = arrayOf(DeliveryListFragmentModule::class))
interface DeliveriesActivitySubcomponent : AndroidInjector<DeliveriesActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DeliveriesActivity>()
}