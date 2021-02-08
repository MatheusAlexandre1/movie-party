package com.bluedot.core.helpers

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.bluedot.core.helpers.ResourceHelper
import com.bluedot.core.preferences.Preferences

class ResourceHelperImpl(private val context: Context,
                         private val preferences: Preferences
) : ResourceHelper {

    override fun getString(resId: Int): String = context.getString(resId)

    override fun getString(resId: Int, vararg formatArgs: Any): String = context.getString(resId, formatArgs)

    override fun getDrawable(resId: Int): Drawable? = ContextCompat.getDrawable(context, resId)

    override fun getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(context, resId)

    override fun getPreferences(): Preferences {
        return preferences.get()
    }

    override fun getContext(): Context {
        return context
    }
}