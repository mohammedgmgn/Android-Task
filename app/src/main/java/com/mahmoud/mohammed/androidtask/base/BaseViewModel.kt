package com.mahmoud.mohammed.androidtask.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.mahmoud.mohammed.androidtask.DeliveryApplication
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }
}