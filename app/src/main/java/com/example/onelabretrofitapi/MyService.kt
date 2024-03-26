package com.example.onelabretrofitapi

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.onelabretrofitapi.worker.MyWorker
import java.util.concurrent.TimeUnit

class MyService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val periodicWorkRequest = PeriodicWorkRequestBuilder<MyWorker>(20, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "PeriodicUpdate",
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )

        return START_STICKY
    }
}