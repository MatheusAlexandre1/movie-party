package com.bluedot.core.base

import android.app.Dialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bluedot.core.helpers.ResourceHelper
import org.koin.android.ext.android.inject
import java.util.prefs.Preferences


abstract class BaseDialogFragment<T : ViewDataBinding> : DialogFragment() {

    lateinit var binding: T

    val preferences: Preferences by inject()

    val resourceHelper: ResourceHelper by inject()

    protected abstract fun getContentLayoutId(): Int

    protected abstract fun initBinding()

    override fun setupDialog(dialog: Dialog, style: Int) {
        binding = DataBindingUtil.inflate(dialog.layoutInflater, getContentLayoutId(), null, false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(binding.root)
        initBinding()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (show || isAdded || dialog != null && dialog!!.isShowing) return

        try {
            show = true
            super.show(manager, tag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun dismiss() {
        try {
            show = false
            super.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private var show = false
    }
}