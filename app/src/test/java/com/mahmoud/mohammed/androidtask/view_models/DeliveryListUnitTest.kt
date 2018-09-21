package com.mahmoud.mohammed.androidtask.view_models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mahmoud.mohammed.androidtask.common.mock
import com.mahmoud.mohammed.androidtask.common.whenever
import com.mahmoud.mohammed.androidtask.domain.DeliveryListUseCase
import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel
import com.mahmoud.mohammed.androidtask.presentation.deliveries.activities.DefaultState
import com.mahmoud.mohammed.androidtask.presentation.deliveries.activities.DeliveryListState
import com.mahmoud.mohammed.androidtask.presentation.deliveries.activities.LoadingState
import com.mahmoud.mohammed.androidtask.presentation.deliveries.viewmodels.DeliveryListViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

class DeliveryListUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val deliveryListUseCases = mock<DeliveryListUseCase>()
    val observerState = mock<Observer<DeliveryListState>>()

    val viewmodel by lazy { DeliveryListViewModel(deliveryListUseCases, Schedulers.trampoline(), Schedulers.trampoline()) }

    @Before
    fun initTest() {
        Mockito.reset(deliveryListUseCases, observerState)
    }

    @Test
    fun testCryptoList_updateCryptoList_LoadOnePage() {
        val response = arrayListOf(DeliveryViewModel())
        whenever(deliveryListUseCases.getDeliveryListBy(ArgumentMatchers.anyInt()))
                .thenReturn(Single.just(response))
        viewmodel.stateLiveData.observeForever(observerState)
        viewmodel.updateDeliveryList()
        val firstPage = 0
        Mockito.verify(deliveryListUseCases).getDeliveryListBy(firstPage)
        val argumentCaptor = ArgumentCaptor.forClass(DeliveryListState::class.java)
        val expectedLoadingState = LoadingState(firstPage, false, emptyList())
        val expectedDefaultState = DefaultState(firstPage + 1, true, response)
        argumentCaptor.run {
            Mockito.verify(observerState, Mockito.times(3)).onChanged(capture())
            val (initialState, loadingState, defaultState) = allValues
            assertEquals(loadingState, expectedLoadingState)
            assertEquals(defaultState, expectedDefaultState)
        }
    }


}
