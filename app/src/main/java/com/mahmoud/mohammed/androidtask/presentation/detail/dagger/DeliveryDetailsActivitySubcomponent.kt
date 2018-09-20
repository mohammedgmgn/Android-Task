package com.mahmoud.mohammed.androidtask.presentation.detail.dagger

import com.mahmoud.mohammed.androidtask.presentation.detail.activities.DeliveryDetailsActivity
import com.mahmoud.mohammed.androidtask.presentation.detail.fragments.dagger.DeliveryDetailsFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(DeliveryDetailsFragmentModule::class))

interface DeliveryDetailsActivitySubcomponent : AndroidInjector<DeliveryDetailsActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DeliveryDetailsActivity>()

}