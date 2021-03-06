package com.mahmoud.mohammed.androidtask.presentation.deliveries.dagger

import com.mahmoud.mohammed.androidtask.dagger.scopes.PerActivity
import com.mahmoud.mohammed.androidtask.data.remote.DeliveryApi
import com.mahmoud.mohammed.androidtask.data.remote.DeliveryDownloader
import com.mahmoud.mohammed.androidtask.data.remote.DeliveryRepository
import com.mahmoud.mohammed.androidtask.domain.DeliveryListInteractor
import com.mahmoud.mohammed.androidtask.domain.DeliveryListUseCase
import com.mahmoud.mohammed.androidtask.presentation.deliveries.fragments.DeliveriesListFragment
import dagger.Module
import dagger.Provides

@Module
class DeliveriesActivityModule {

    @Provides
    @PerActivity
    fun provideDeliveriesListFragment(): DeliveriesListFragment {
        return DeliveriesListFragment()
    }

    @Provides
    @PerActivity
    fun providesDeliveryRepository(deliveryApi: DeliveryApi): DeliveryRepository {
        return DeliveryDownloader(deliveryApi)
    }

    @Provides
    @PerActivity
    fun providesDeliveryListUseCases(deliveryRepository: DeliveryRepository): DeliveryListUseCase {
        return DeliveryListInteractor(deliveryRepository)
    }



}