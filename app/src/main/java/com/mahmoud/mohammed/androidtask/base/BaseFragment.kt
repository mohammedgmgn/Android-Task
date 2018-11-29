package com.mahmoud.mohammed.androidtask.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mahmoud.mohammed.androidtask.DeliveryApplication
import com.mahmoud.mohammed.androidtask.R
import com.mahmoud.mohammed.androidtask.data.model.DeliveryModel
import com.mahmoud.mohammed.androidtask.presentation.detail.activities.DeliveryDetailsActivity

open class BaseFragment: Fragment()  {
    protected fun navigateToDeliveriesDetailsScreen(delivery: DeliveryModel) {
        startActivity(DeliveryDetailsActivity.newIntent(
                context!!,
                delivery))
        activity?.overridePendingTransition(0, 0)
    }
    protected fun showMessage(msg:String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}