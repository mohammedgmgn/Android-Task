package com.mahmoud.mohammed.androidtask.presentation.deliveries.dagger

import androidx.fragment.app.Fragment
import com.mahmoud.mohammed.androidtask.presentation.deliveries.fragments.DeliveriesListFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Subcomponent/*(modules = ...)*/
interface DeliveryListFragmentSubcomponent: AndroidInjector<DeliveriesListFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<DeliveriesListFragment>()
}

@Module(subcomponents = arrayOf(DeliveryListFragmentSubcomponent::class))
abstract class DeliveryListFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(DeliveriesListFragment::class)
    abstract fun bindDeliveryListFragmentInjectorFactory(builder: DeliveryListFragmentSubcomponent.Builder):
            AndroidInjector.Factory<out Fragment>
}