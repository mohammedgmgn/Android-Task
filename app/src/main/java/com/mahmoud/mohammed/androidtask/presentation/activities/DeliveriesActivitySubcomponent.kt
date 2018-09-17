package com.mahmoud.mohammed.androidtask.presentation.activities

import com.mahmoud.mohammed.androidtask.presentation.activities.DeliveriesActivity
import com.mahmoud.mohammed.androidtask.presentation.fragments.DeliveryListFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector



@Subcomponent(modules = arrayOf(DeliveryListFragmentModule::class))
interface DeliveriesActivitySubcomponent : AndroidInjector<DeliveriesActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DeliveriesActivity>()
}