package com.mahmoud.mohammed.androidtask.common

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.widget.Toast


class ConnectionStateMonitor : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notConnected = intent.getBooleanExtra(ConnectivityManager
                .EXTRA_NO_CONNECTIVITY, false)
        if (notConnected) {
            //   disconnected()
            Toast.makeText(context, "Not connected", Toast.LENGTH_SHORT).show()

        } else {
            //       connected()
            Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show()

        }
    }


}