package com.example.onelabretrofitapi.core

import android.util.Log
import com.example.onelabretrofitapi.core.functional.State

open class BaseRepository {

    protected suspend fun <T : Any> apiCall(call: suspend () -> T): State<Throwable, T> =
        try {
            State.Success(call.invoke())
        } catch (throwable: Throwable) {
            Log.e("apiCall", "error", throwable)
            State.Error(throwable)
        }
}