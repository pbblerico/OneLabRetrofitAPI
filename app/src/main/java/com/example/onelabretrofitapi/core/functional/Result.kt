package com.example.onelabretrofitapi.core.functional

import android.util.Log

sealed class Result<out E, out D> {
    data class Error<out E>(val a: E) : Result<E, Nothing>()

    data class Success<out D>(val b: D) : Result<Nothing, D>()
}

inline fun <E, D> Result<E, D>.onSuccess(action: (D) -> Unit): Result<E, D> {
    if (this is Result.Success) action(b)
    return this
}

inline fun <E, D> Result<E, D>.onFailure(action: (E) -> Unit): Result<E, D> {
    if (this is Result.Error) {
        if (a is Throwable) {
            Log.e("Either#onFailure","Either#onFailure called", a)
        }
        action(a)
    }
    return this
}