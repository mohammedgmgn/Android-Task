package com.mahmoud.mohammed.androidtask.presentation.deliveries.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mahmoud.mohammed.androidtask.R
import com.mahmoud.mohammed.androidtask.common.replaceFragment
import com.mahmoud.mohammed.androidtask.presentation.deliveries.fragments.DELIVERIES_LIST_FRAGMENT_TAG
import com.mahmoud.mohammed.androidtask.presentation.deliveries.fragments.newDeliveriesListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DeliveriesActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    val deliveryListFragment by lazy { newDeliveriesListFragment() }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            replaceFragment(R.id.container, deliveryListFragment, DELIVERIES_LIST_FRAGMENT_TAG)
    }
}
