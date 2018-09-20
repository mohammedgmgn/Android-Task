package com.mahmoud.mohammed.androidtask.presentation.detail.fragments.dagger

import androidx.fragment.app.Fragment
import com.mahmoud.mohammed.androidtask.presentation.detail.fragments.DeliveryDetailsFragment
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Subcomponent/*(modules = ...)*/

interface DeliveryDetailsFragmentSubcomponent : AndroidInjector<DeliveryDetailsFragment> {
    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<DeliveryDetailsFragment>()

}
@Module(subcomponents = arrayOf(DeliveryDetailsFragmentSubcomponent::class))
abstract class DeliveryDetailsFragmentModule{
    @Binds
    @IntoMap
    @FragmentKey(DeliveryDetailsFragment::class)
    abstract fun bindDeliveryDetailsFragmentInjectorFactory
            (builder: DeliveryDetailsFragmentSubcomponent.Builder): AndroidInjector.Factory<out Fragment>


}
