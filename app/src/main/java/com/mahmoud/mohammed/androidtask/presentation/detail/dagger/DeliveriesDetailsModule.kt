package com.mahmoud.mohammed.androidtask.presentation.detail.dagger

import com.mahmoud.mohammed.androidtask.dagger.scopes.PerActivity
import com.mahmoud.mohammed.androidtask.presentation.detail.fragments.DeliveryDetailsFragment
import dagger.Module
import dagger.Provides

@Module
 class DeliveriesDetailsModule {
    @Provides
    @PerActivity
    fun provideDeliveryDetailsFragment(): DeliveryDetailsFragment {
        return DeliveryDetailsFragment()
    }
}