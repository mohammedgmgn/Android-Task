package com.mahmoud.mohammed.androidtask.common

import com.mahmoud.mohammed.androidtask.data.model.Delivery
import com.mahmoud.mohammed.androidtask.data.model.Location
import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel
import com.mahmoud.mohammed.androidtask.domain.LIMIT_DELIVERY_LIST
import java.util.*
import kotlin.collections.ArrayList

fun oneSizeArrayEmptyDeliveryViewModel(): List<DeliveryViewModel> =
    ArrayList<DeliveryViewModel>(Collections.nCopies(1, DeliveryViewModel()))

fun limitDeliveryListSizeArrayEmptyDeliveryViewModel(): List<DeliveryViewModel> =
    ArrayList<DeliveryViewModel>(Collections.nCopies(LIMIT_DELIVERY_LIST, DeliveryViewModel()))

fun deliveryToPOJOmodel() =
        Delivery(1, "Deliver food to Eric",
                "https://s3-ap-southeast-1.amazonaws.com/lalamove-mock-api/images/pet-4.jpeg",
                Location(22.336093,114.155288,"Cheung Sha Wan"))

fun deliveryViewModelFrom(delivery:Delivery) =
        DeliveryViewModel(delivery.id, delivery.description, delivery.imageUrl, delivery.location.lat,
                delivery.location.lng, delivery.location.address)