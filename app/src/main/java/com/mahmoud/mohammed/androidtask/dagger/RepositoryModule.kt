package com.mahmoud.mohammed.androidtask.dagger

import com.mahmoud.mohammed.androidtask.data.DeliveryApi
import com.mahmoud.mohammed.androidtask.data.DeliveryDownloader
import com.mahmoud.mohammed.androidtask.data.DeliveryRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesDeliveryRepository(deliveryApi: DeliveryApi):
            DeliveryRepository = DeliveryDownloader(deliveryApi)

}