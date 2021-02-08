package com.bluedot.core.base

sealed class Response<out V> {
    data class Success<out V>(val value: V) : Response<V>()
    data class Failure(val error: Throwable) : Response<Nothing>()

    private fun <V> success(value: V): Response<V> = Success(value)
    private fun failure(value: Throwable): Response<Nothing> = Failure(value)

    fun <V> response(action: () -> V): Response<V> =
        try {
            success(action())
        } catch (e: Exception) {
            failure(e)
        }
}

inline fun <V, A> Response<V>.fold(e: (Throwable) -> A, v: (V) -> A): A = when (this) {
    is Response.Failure -> e(this.error)
    is Response.Success -> v(this.value)
}