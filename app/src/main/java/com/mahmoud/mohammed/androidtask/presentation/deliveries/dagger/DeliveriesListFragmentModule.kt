package com.mahmoud.mohammed.androidtask.presentation.deliveries.dagger

import com.mahmoud.mohammed.androidtask.dagger.scopes.PerActivity
import com.mahmoud.mohammed.androidtask.dagger.scopes.PerFragment
import com.mahmoud.mohammed.androidtask.data.remote.DeliveryApi
import com.mahmoud.mohammed.androidtask.data.remote.DeliveryDownloader
import com.mahmoud.mohammed.androidtask.data.remote.DeliveryRepository
import com.mahmoud.mohammed.androidtask.domain.DeliveryListInteractor
import com.mahmoud.mohammed.androidtask.domain.DeliveryListUseCase
import dagger.Module
import dagger.Provides

@Module
class DeliveriesListFragmentModule {
    @Provides
    fun providesDeliveryRepository(deliveryApi: DeliveryApi): DeliveryRepository {
        return DeliveryDownloader(deliveryApi)
    }
    @Provides
    fun providesDeliveryListUseCases(deliveryRepository: DeliveryRepository): DeliveryListUseCase {
        return DeliveryListInteractor(deliveryRepository)
    }

}