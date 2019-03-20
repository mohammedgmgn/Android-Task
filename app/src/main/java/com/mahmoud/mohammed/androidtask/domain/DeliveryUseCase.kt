package com.mahmoud.mohammed.androidtask.domain
import com.mahmoud.mohammed.androidtask.data.model.DeliveryModel
import io.reactivex.Single

interface DeliveryListUseCase{
    fun getDeliveryListBy(page: Int): Single<List<DeliveryModel>>
}
val emptyDeliveryViewModel = DeliveryModel()

