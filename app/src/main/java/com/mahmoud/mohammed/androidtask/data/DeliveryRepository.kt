package com.mahmoud.mohammed.androidtask.data

import com.mahmoud.mohammed.androidtask.data.model.Delivery
import io.reactivex.Single

interface DeliveryRepository {

    fun getDeliveryList(page:Int, limit:Int) : Single<List<Delivery>>
}