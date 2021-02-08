package com.bluedot.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.bluedot.core.R
import com.bluedot.core.dialog.ErrorDialog
import com.bluedot.core.dialog.LoadingDialog
import com.bluedot.core.preferences.Preferences
import com.bluedot.core.helpers.ResourceHelper
import com.bluedot.core.model.exception.ApiException
import com.bluedot.core.model.exception.TMDBException
import com.bluedot.core.utils.Constants
import com.bluedot.core.utils.NetworkUtils
import org.koin.android.ext.android.inject
import retrofit2.HttpException

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    lateinit var binding: T

    val preferences: Preferences by inject()

    val resourceHelper: ResourceHelper by inject()

    private val loading: LoadingDialog by inject()
    private val errorDialog: ErrorDialog by inject()

    protected abstract fun getContentLayoutId(): Int

    protected abstract fun initBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getContentLayoutId(), container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        initBinding()
        return binding.root
    }

    fun configureViewModel(viewModel: BaseViewModel) {
        viewModel.showLoading.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                if (viewModel.isLoading()) {
                    loading.show(childFragmentManager, Constants.SHOW_LOADING)
                } else {
                    loading.dismiss()
                }
            }
        })
    }

    fun handleError(error: Throwable) {
        var message: String = when (error) {
            is HttpException -> resourceHelper.getString(R.string.api_general_error)
            is TMDBException, is ApiException -> error.message ?: ""
            else -> resourceHelper.getString(R.string.api_general_error)
        }

        if (!NetworkUtils.hasConnection(resourceHelper.getContext()))
            message = resourceHelper.getString(R.string.network_error)

        errorDialog.setTitle(resourceHelper.getString(R.string.error))
        errorDialog.setMessage(message)
        errorDialog.show(childFragmentManager, Constants.ERROR_DIALOG)
    }
}