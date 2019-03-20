/*
package com.mahmoud.mohammed.androidtask.use_cases

import com.mahmoud.mohammed.androidtask.data.remote.DeliveryRepository
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mahmoud.mohammed.androidtask.common.deliveryToPOJOmodel
import com.mahmoud.mohammed.androidtask.common.deliveryViewModelFrom
import com.mahmoud.mohammed.androidtask.common.mock
import com.mahmoud.mohammed.androidtask.common.whenever
import com.mahmoud.mohammed.androidtask.domain.DeliveryListInteractor

class DeliveryListUseCasesUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val deliveryRepository = mock<DeliveryRepository>()

    val deliveryListUseCases by lazy { DeliveryListInteractor(deliveryRepository) }

    @Test
    fun testDeliveryListUseCases_getDeliveryList_Completed() {
        whenever(deliveryRepository.getDeliveryList(anyInt(), anyInt()))
                .thenReturn(Single.just(emptyList()))

        deliveryListUseCases.getDeliveryListBy(0)
                .test()
                .assertComplete()
    }

    @Test
    fun testDeliveryListUseCases_getDeliveryList_Error() {
        val response = Throwable("Error response")
        whenever(deliveryRepository.getDeliveryList(anyInt(), anyInt()))
                .thenReturn(Single.error(response))

        deliveryListUseCases.getDeliveryListBy(0)
                .test()
                .assertError(response)

    }

    @Test
    fun testDeliveryListUseCases_getDeliveryList_response() {
        val response = arrayListOf(deliveryToPOJOmodel())
        whenever(deliveryRepository.getDeliveryList(anyInt(), anyInt()))
                .thenReturn(Single.just(response))
        val expectedList = arrayListOf(deliveryViewModelFrom(deliveryToPOJOmodel()))
        deliveryListUseCases.getDeliveryListBy(0)
                .test()
                .assertValue(expectedList)
    }
}*/
