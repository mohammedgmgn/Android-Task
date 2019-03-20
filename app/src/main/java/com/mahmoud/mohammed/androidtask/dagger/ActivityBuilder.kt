package com.mahmoud.mohammed.androidtask.dagger

import com.mahmoud.mohammed.androidtask.dagger.scopes.PerActivity
import com.mahmoud.mohammed.androidtask.presentation.deliveries.activities.DeliveriesActivity
import com.mahmoud.mohammed.androidtask.presentation.deliveries.dagger.DeliveriesListFragmentModule
import com.mahmoud.mohammed.androidtask.presentation.detail.dagger.DeliveriesDetailsModule
import com.mahmoud.mohammed.androidtask.presentation.deliveries.dagger.DeliveriesActivityModule
import com.mahmoud.mohammed.androidtask.presentation.deliveries.fragments.DeliveriesListFragment
import com.mahmoud.mohammed.androidtask.presentation.detail.activities.DeliveryDetailsActivity
import com.mahmoud.mohammed.androidtask.presentation.detail.fragments.DeliveryDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @PerActivity
    @ContributesAndroidInjector(modules = [DeliveriesActivityModule::class,FragmentsModule::class])
    abstract fun bindMainActivity(): DeliveriesActivity
    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentsModule::class, DeliveriesDetailsModule::class])
    abstract fun bindDeliveryDetailsActivity(): DeliveryDetailsActivity
}

@Module
abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract fun provideDeliveriesListFragment(): DeliveriesListFragment
    @ContributesAndroidInjector
    abstract fun provideDeliveryDetailsFragment(): DeliveryDetailsFragment
}
