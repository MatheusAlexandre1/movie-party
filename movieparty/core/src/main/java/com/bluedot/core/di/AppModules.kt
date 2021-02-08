package com.bluedot.core.di

import com.bluedot.core.helpers.ResourceHelper
import com.bluedot.core.helpers.ResourceHelperImpl
import com.bluedot.core.preferences.Preferences
import com.bluedot.core.preferences.PreferencesImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<Preferences> { PreferencesImpl() }
    single<ResourceHelper> {
        ResourceHelperImpl(
            context = androidContext(),
            preferences = get()
        )
    }
}

