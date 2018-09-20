package com.mahmoud.mohammed.androidtask.data.remote

import com.mahmoud.mohammed.androidtask.data.model.Delivery
import io.reactivex.Single

class DeliveryDownloader(private val deliveryApi: DeliveryApi) : DeliveryRepository {
    override fun getDeliveryList(page: Int, limit: Int): Single<List<Delivery>>
            = deliveryApi.getDeliveryList(page * limit, limit)

}