package com.bluedot.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object NetworkUtils {
    fun hasConnection(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                }
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    when (type) {
                        ConnectivityManager.TYPE_WIFI -> return true
                        ConnectivityManager.TYPE_MOBILE -> return true
                        ConnectivityManager.TYPE_ETHERNET -> return true
                        else -> return false
                    }
                }
                return false
            }
        }
        return false
    }
}