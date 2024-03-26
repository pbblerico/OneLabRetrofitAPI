package com.example.onelabretrofitapi.worker

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.onelabretrofitapi.CustomBroadcastReceiver


class MyWorker(
     private val appContext: Context,
     workerParams: WorkerParameters)
: Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val intent = Intent(applicationContext, CustomBroadcastReceiver::class.java).apply {
            action = "TEST_ACTION"
        }
        Log.d("re", "helo")
        appContext.sendBroadcast(intent)
        return Result.success()
    }

}