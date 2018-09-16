package com.mahmoud.mohammed.androidtask.dagger

import com.mahmoud.mohammed.androidtask.data.DeliveryRepository
import com.mahmoud.mohammed.androidtask.domain.DeliveryListInteractor
import com.mahmoud.mohammed.androidtask.domain.DeliveryListUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun providesDeliveryListUseCases
            (deliveryRepository: DeliveryRepository):
            DeliveryListUseCase = DeliveryListInteractor(deliveryRepository)
}
