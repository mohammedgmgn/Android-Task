package com.mahmoud.mohammed.androidtask

import com.mahmoud.mohammed.androidtask.data.DeliveryRepository
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mahmoud.mohammed.androidtask.domain.DeliveryListInteractor
import org.mockito.Mock
import org.mockito.Mockito.mock

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
}