package com.mahmoud.mohammed.androidtask.presentation.deliveries.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mahmoud.mohammed.androidtask.domain.DeliveryListUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named
import com.mahmoud.mohammed.androidtask.dagger.application.SCHEDULER_IO
import com.mahmoud.mohammed.androidtask.dagger.application.SCHEDULER_MAIN_THREAD
import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel
import com.mahmoud.mohammed.androidtask.domain.LIMIT_DELIVERY_LIST
import com.mahmoud.mohammed.androidtask.presentation.deliveries.activities.*

class DeliveryListViewModel @Inject constructor(private val deliveryListUseCases: DeliveryListUseCase,
                                                @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
                                                @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler)
    : ViewModel()
{

    val stateLiveData =  MutableLiveData<DeliveryListState>()
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
    @SuppressLint("CheckResult")
    private fun getDeliveryList(page:Int) {
       deliveryListUseCases.getDeliveryListBy(page)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(this::onDeliveryListReceived, this::onError)
    }
    private fun onDeliveryListReceived(cryptoList: List<DeliveryViewModel>) {
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