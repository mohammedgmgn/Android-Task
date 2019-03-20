package com.mahmoud.mohammed.androidtask.presentation.deliveries.activities

import com.mahmoud.mohammed.androidtask.data.model.DeliveryModel


sealed class DeliveryListState {
    abstract val pageNum:Int
    abstract val loadedAllItems:Boolean
    abstract val data: List<DeliveryModel>
}
data class DefaultState(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<DeliveryModel>) : DeliveryListState()
data class LoadingState(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<DeliveryModel>) : DeliveryListState()
data class PaginatingState(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<DeliveryModel>) : DeliveryListState()
data class ErrorState(val errorMessage: String, override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<DeliveryModel>) : DeliveryListState()