package com.mahmoud.mohammed.androidtask.presentation.deliveries.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mahmoud.mohammed.androidtask.base.BaseViewModel
import com.mahmoud.mohammed.androidtask.data.model.DeliveryModel
import com.mahmoud.mohammed.androidtask.domain.DeliveryListUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import com.mahmoud.mohammed.androidtask.domain.LIMIT_DELIVERY_LIST
import com.mahmoud.mohammed.androidtask.presentation.deliveries.activities.*

class DeliveryListViewModel @Inject constructor(
        application: Application,
        private val deliveryListUseCases: DeliveryListUseCase,
        private val subscribeOnScheduler: Scheduler,
        private val observeOnScheduler: Scheduler)
    : BaseViewModel(application) {

    val stateLiveData = MutableLiveData<DeliveryListState>()

    init {
        stateLiveData.value = DefaultState(0, false, emptyList())
    }

    fun updateDeliveryList() {
        val pageNum = obtainCurrentPageNum()
        stateLiveData.value = if (pageNum == 0)
            LoadingState(pageNum, false, obtainCurrentData())
        else
            PaginatingState(pageNum, false, obtainCurrentData())
        getDeliveryList(pageNum)
    }

    fun restoreDeliveryList() {
        val pageNum = obtainCurrentPageNum()
        stateLiveData.value = DefaultState(pageNum, false, obtainCurrentData())
    }

    private fun obtainCurrentPageNum() = stateLiveData.value?.pageNum ?: 0
    private fun obtainCurrentData() = stateLiveData.value?.data ?: emptyList()
    private fun getDeliveryList(page: Int) {

        addDisposable(deliveryListUseCases.getDeliveryListBy(page)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(this::onDeliveryListReceived, this::onError))
    }

    private fun onDeliveryListReceived(cryptoList: List<DeliveryModel>) {
        val currentCryptoList = obtainCurrentData().toMutableList()
        val currentPageNum = obtainCurrentPageNum() + 1
        val areAllItemsLoaded = cryptoList.size < LIMIT_DELIVERY_LIST
        currentCryptoList.addAll(cryptoList)
        stateLiveData.value = DefaultState(currentPageNum, areAllItemsLoaded, currentCryptoList)
    }

    private fun onError(error: Throwable) {
        val pageNum = stateLiveData.value?.pageNum ?: 0
        stateLiveData.value = ErrorState(error.message
                ?: "", pageNum,
                obtainCurrentLoadedAllItems(), obtainCurrentData())
    }

    private fun obtainCurrentLoadedAllItems() = stateLiveData.value?.loadedAllItems ?: false

}