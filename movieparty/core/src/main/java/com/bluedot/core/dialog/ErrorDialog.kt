package com.bluedot.core.dialog

import com.bluedot.core.base.BaseDialogFragment
import com.bluedot.core.R
import com.bluedot.core.databinding.DialogErrorBinding
import com.bluedot.core.utils.Constants


class ErrorDialog : BaseDialogFragment<DialogErrorBinding>() {

    private var mTitle = Constants.NO_VALUE_STRING
    private var mDescription = Constants.NO_VALUE_STRING

    override fun getContentLayoutId(): Int = R.layout.dialog_error

    override fun initBinding() {
        setupUI()
        binding.btnUnderstood.setOnClickListener { dismiss() }
    }

    private fun setupUI() {
        binding.tvTitle.text = mTitle
        binding.tvDescription.text = mDescription
    }

    fun setTitle(title: String) {
        mTitle = title
    }

    fun setMessage(message: String) {
        mDescription = message
    }
}
