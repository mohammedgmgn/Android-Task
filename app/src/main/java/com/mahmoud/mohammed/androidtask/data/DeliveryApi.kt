package com.mahmoud.mohammed.androidtask.data

import com.mahmoud.mohammed.androidtask.data.model.Delivery
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DeliveryApi {
    @GET("/deliveries")
    fun getDeliveryList(@Query("offset" )start:Int, @Query("limit") limit:Int) : Single<List<Delivery>>

}