package com.bluedot.core.helpers

import com.bluedot.core.helpers.ResourceHelper
import org.koin.core.KoinComponent
import org.koin.core.get

object Resources: KoinComponent {
    var resourceHelper: ResourceHelper = get()
}