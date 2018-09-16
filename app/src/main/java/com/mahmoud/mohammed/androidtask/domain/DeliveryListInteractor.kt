package com.mahmoud.mohammed.androidtask.domain

import com.mahmoud.mohammed.androidtask.data.DeliveryRepository
import com.mahmoud.mohammed.androidtask.data.model.Delivery
import io.reactivex.Single

const val LIMIT_CRYPTO_LIST = 20
class DeliveryListInteractor(private val deliveryRepository: DeliveryRepository) : DeliveryListUseCase {
    override fun getDeliveryListBy(page: Int): Single<List<DeliveryViewModel>> {
        return deliveryRepository.getDeliveryList(page, LIMIT_CRYPTO_LIST)
                .map { deliveries -> deliveries.map(deliveryViewModelMapper) }
    }
    val deliveryViewModelMapper: (Delivery) -> DeliveryViewModel = {
        delivery   -> DeliveryViewModel(delivery.id, delivery.description, delivery.imageUrl, delivery.location.lat,
            delivery.location.lng, delivery.location.address)
    }

}