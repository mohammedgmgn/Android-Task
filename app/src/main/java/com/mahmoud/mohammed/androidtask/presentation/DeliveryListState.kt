package com.mahmoud.mohammed.androidtask.presentation

import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel


sealed class DeliveryListState {
    abstract val pageNum:Int
    abstract val loadedAllItems:Boolean
    abstract val data: List<DeliveryViewModel>
}
data class DefaultState(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<DeliveryViewModel>) : DeliveryListState()
data class LoadingState(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<DeliveryViewModel>) : DeliveryListState()
data class PaginatingState(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<DeliveryViewModel>) : DeliveryListState()
data class ErrorState(val errorMessage: String, override val pageNum: Int, override val loadedAllItems: Boolean, override val data: List<DeliveryViewModel>) : DeliveryListState()