package com.mahmoud.mohammed.androidtask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahmoud.mohammed.androidtask.R

class DeliveriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar_main))
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, newDeliveriesListFragment(), DELIVERIES_LIST_FRAGMENT_TAG)
                    .commitNow()
            title = getString(R.string.main_title)
        }
    }
}
