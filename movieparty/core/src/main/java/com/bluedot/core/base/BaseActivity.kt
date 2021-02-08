package com.bluedot.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import com.bluedot.core.R
import com.bluedot.core.dialog.ErrorDialog
import com.bluedot.core.dialog.LoadingDialog
import com.bluedot.core.preferences.Preferences
import com.bluedot.core.helpers.ResourceHelper
import com.bluedot.core.model.exception.ApiException
import com.bluedot.core.model.exception.TMDBException
import com.bluedot.core.utils.Constants
import org.koin.android.ext.android.inject
import retrofit2.HttpException

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    val preferences: Preferences by inject()

    val resourceHelper: ResourceHelper by inject()

    private val loading: LoadingDialog by inject()
    val errorDialog: ErrorDialog by inject()

    lateinit var binding: T

    protected abstract fun getContentLayoutId(): Int

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getContentLayoutId())
        binding.apply {
            lifecycleOwner = this@BaseActivity
        }

        init()
    }

    fun configureViewModel(viewModel: BaseViewModel) {
        viewModel.showLoading.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                if (viewModel.isLoading()) {
                    loading.show(supportFragmentManager, Constants.SHOW_LOADING)
                } else {
                    loading.dismiss()
                }
            }
        })
    }

    fun handleError(error: Throwable) {
        val message: String = when (error) {
            is HttpException -> getString(R.string.api_general_error)
            is TMDBException, is ApiException -> error.message ?: ""
            else -> getString(R.string.api_general_error)
        }
        errorDialog.setTitle(resourceHelper.getString(R.string.error))
        errorDialog.setMessage(message)
        errorDialog.show(supportFragmentManager, Constants.ERROR_DIALOG)
    }
}