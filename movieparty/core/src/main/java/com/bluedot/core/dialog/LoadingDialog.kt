package com.bluedot.core.dialog

import com.bluedot.core.R
import com.bluedot.core.base.BaseDialogFragment
import com.bluedot.core.databinding.DialogLoadingBinding


class LoadingDialog : BaseDialogFragment<DialogLoadingBinding>() {

    override fun getContentLayoutId(): Int = R.layout.dialog_loading

    override fun initBinding() {
    }
}
