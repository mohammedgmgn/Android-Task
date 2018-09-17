package com.mahmoud.mohammed.androidtask.presentation.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mahmoud.mohammed.androidtask.R
import dagger.android.support.AndroidSupportInjection

fun newDeliveriesListFragment() = DeliveriesListFragment()
val DELIVERIES_LIST_FRAGMENT_TAG = DeliveriesListFragment::class.java.name

class DeliveriesListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deliveries_list, container, false)
    }
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}
