package com.bluedot.core.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    var showLoading = ObservableBoolean(false)

    fun showLoading() {
        showLoading.set(true)
    }

    fun hideLoading() {
        showLoading.set(false)
    }

    fun isLoading(): Boolean {
        return this.showLoading.get()
    }
}
