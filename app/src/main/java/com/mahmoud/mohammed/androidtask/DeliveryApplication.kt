package com.mahmoud.mohammed.androidtask

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.mahmoud.mohammed.androidtask.dagger.NetworkModule
import com.mahmoud.mohammed.androidtask.dagger.application.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DeliveryApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}
