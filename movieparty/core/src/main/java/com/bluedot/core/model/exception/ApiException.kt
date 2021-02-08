package com.bluedot.core.model.exception

import java.io.IOException

class ApiException : IOException() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return this.message.equals((other as Exception).message)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
