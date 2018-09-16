package com.mahmoud.mohammed.androidtask.data.model

import com.google.gson.annotations.SerializedName

data class Delivery(@SerializedName("id") val id: Int,
                    @SerializedName("description") val description: String,
                    @SerializedName("imageUrl") val imageUrl: String,
                    @SerializedName("location") val location: Location
)