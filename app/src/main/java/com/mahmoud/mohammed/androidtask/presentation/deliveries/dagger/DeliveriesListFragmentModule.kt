package com.mahmoud.mohammed.androidtask.presentation.deliveries.dagger
import com.mahmoud.mohammed.androidtask.common.imagehelper.ImageLoader
import com.mahmoud.mohammed.androidtask.data.model.DeliveryModel
import com.mahmoud.mohammed.androidtask.data.remote.DeliveryApi
import com.mahmoud.mohammed.androidtask.data.remote.DeliveryDownloader
import com.mahmoud.mohammed.androidtask.data.remote.DeliveryRepository
import com.mahmoud.mohammed.androidtask.domain.DeliveryListInteractor
import com.mahmoud.mohammed.androidtask.domain.DeliveryListUseCase
import com.mahmoud.mohammed.androidtask.presentation.common.DeliveryListAdapter
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
    @Provides
    fun provideDeliveryListAdapter(imageLoader: ImageLoader): DeliveryListAdapter {
        return DeliveryListAdapter(imageLoader)
    }
    @Provides
    fun provideDeliveryModel(): DeliveryModel {
        return DeliveryModel()
    }

}