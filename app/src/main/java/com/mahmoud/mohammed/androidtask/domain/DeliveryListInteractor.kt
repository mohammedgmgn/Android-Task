package com.mahmoud.mohammed.androidtask.domain

import com.mahmoud.mohammed.androidtask.data.remote.DeliveryRepository
import com.mahmoud.mohammed.androidtask.data.model.Delivery
import com.mahmoud.mohammed.androidtask.data.model.DeliveryModel
import io.reactivex.Single
import javax.inject.Inject

const val LIMIT_DELIVERY_LIST = 20
// applying Delegate in kotlin
class DeliveryListInteractor(deliveryRepository: DeliveryRepository) : DeliveryRepository by deliveryRepository, DeliveryListUseCase {

    override fun getDeliveryListBy(page: Int): Single<List<DeliveryModel>> {
        return getDeliveryList(page, LIMIT_DELIVERY_LIST).map { deliveries -> deliveries.map(deliveryViewModelMapper) }
    }

    val deliveryViewModelMapper: (Delivery) -> DeliveryModel = { delivery ->
        DeliveryModel(delivery.id, delivery.description, delivery.imageUrl, delivery.location.lat,
                delivery.location.lng, delivery.location.address)
    }

}