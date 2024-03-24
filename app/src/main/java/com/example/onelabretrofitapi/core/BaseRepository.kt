package com.example.onelabretrofitapi.core

import android.util.Log
import com.example.onelabretrofitapi.core.functional.Result

open class BaseRepository {

    protected suspend fun <T : Any> apiCall(call: suspend () -> T): Result<Throwable, T> =
        try {
            Result.Success(call.invoke())
        } catch (throwable: Throwable) {
            Log.e("apiCall", "error", throwable)
            Result.Error(throwable)
        }
}