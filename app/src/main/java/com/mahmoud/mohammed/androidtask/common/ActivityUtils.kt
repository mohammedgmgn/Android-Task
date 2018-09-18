package com.mahmoud.mohammed.androidtask.common

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(@IdRes where: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
            .replace(where, fragment, tag)
            .commit()
}
fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}
fun getCachSize(context: Context):Long{
    var size: Long = 0
    val cacheDirectory = context.getCacheDir()
    val files = cacheDirectory.listFiles()
    for (f in files) {
        size = size + f.length()
    }
return size
}
