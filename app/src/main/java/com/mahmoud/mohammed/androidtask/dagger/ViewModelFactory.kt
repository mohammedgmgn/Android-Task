package com.mahmoud.mohammed.androidtask.dagger

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahmoud.mohammed.androidtask.base.BaseViewModel
import com.mahmoud.mohammed.androidtask.dagger.application.SCHEDULER_IO
import com.mahmoud.mohammed.androidtask.dagger.application.SCHEDULER_MAIN_THREAD
import com.mahmoud.mohammed.androidtask.domain.DeliveryListUseCase
import com.mahmoud.mohammed.androidtask.presentation.deliveries.viewmodels.DeliveryListViewModel
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named



@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val application: Application,
                                           @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
                                           @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler,
                                           private val deliveryListUseCase: DeliveryListUseCase) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(DeliveryListViewModel::class.java)  ->
                DeliveryListViewModel(application,deliveryListUseCase,subscribeOnScheduler,observeOnScheduler) as T
            else -> BaseViewModel(application) as T
        }
    }
}
