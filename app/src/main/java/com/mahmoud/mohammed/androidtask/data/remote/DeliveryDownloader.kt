package com.mahmoud.mohammed.androidtask.data.remote

import com.mahmoud.mohammed.androidtask.data.model.Delivery
import io.reactivex.Single
import javax.inject.Inject

class DeliveryDownloader@Inject constructor(private val deliveryApi: DeliveryApi) : DeliveryRepository {
    override fun getDeliveryList(page: Int, limit: Int): Single<List<Delivery>>
            = deliveryApi.getDeliveryList(page * limit, limit)

}