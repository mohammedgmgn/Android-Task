package com.mahmoud.mohammed.androidtask.base

import androidx.fragment.app.Fragment
import com.mahmoud.mohammed.androidtask.domain.DeliveryViewModel
import com.mahmoud.mohammed.androidtask.presentation.detail.DeliveryDetailsActivity

open class BaseFragment: Fragment() {

    protected fun navigateToDeliveriesDetailsScreen(delivery: DeliveryViewModel) {


        startActivity(DeliveryDetailsActivity.newIntent(
                context!!,
                delivery))
        activity?.overridePendingTransition(0, 0)

    }
}