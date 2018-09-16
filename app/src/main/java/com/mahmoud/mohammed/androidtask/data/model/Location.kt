package com.mahmoud.mohammed.androidtask.data.model

import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double,
    @SerializedName("address") val address: String
)