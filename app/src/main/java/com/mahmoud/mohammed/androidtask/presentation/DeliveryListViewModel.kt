package com.mahmoud.mohammed.androidtask.presentation

import androidx.lifecycle.ViewModel
import com.mahmoud.mohammed.androidtask.domain.DeliveryListUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named
import com.mahmoud.mohammed.androidtask.dagger.application.SCHEDULER_IO
import com.mahmoud.mohammed.androidtask.dagger.application.SCHEDULER_MAIN_THREAD

class DeliveryListViewModel @Inject constructor(private val deliveryListUseCases: DeliveryListUseCase,
                                                @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler,
                                                @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler)
    : ViewModel()