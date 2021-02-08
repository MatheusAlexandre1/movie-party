package com.bluedot.core.di

import com.bluedot.core.dialog.ErrorDialog
import com.bluedot.core.dialog.LoadingDialog
import org.koin.dsl.module


val utilsModule = module {
    single { LoadingDialog() }
    single { ErrorDialog() }
}