package com.mahmoud.mohammed.androidtask.data.model

import android.os.Parcel
import android.os.Parcelable


typealias DeliveryId=Int
typealias DescriptionContent=String
typealias ImagePath=String
typealias AddressInfo=String
data class DeliveryModel(val id: DeliveryId,
                         val description: DescriptionContent,
                         val imageUrl: ImagePath,
                         val lat: Double, val lng: Double,val address:AddressInfo
) : Parcelable {
    constructor() : this(0, "", "", 0.0, 0.0,"")
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readDouble(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(description)
        writeString(imageUrl)
        writeDouble(lat)
        writeDouble(lng)
        writeString(address)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DeliveryModel> = object : Parcelable.Creator<DeliveryModel> {
            override fun createFromParcel(source: Parcel): DeliveryModel = DeliveryModel(source)
            override fun newArray(size: Int): Array<DeliveryModel?> = arrayOfNulls(size)
        }
    }
}