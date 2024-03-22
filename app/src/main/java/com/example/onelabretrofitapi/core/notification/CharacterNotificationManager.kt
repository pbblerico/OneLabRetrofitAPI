package com.example.onelabretrofitapi.core.notification

import android.app.NotificationManager
import android.content.Context
import javax.inject.Inject

class CharacterNotificationManager @Inject constructor(
    private val context: Context,
    private val notificationManager: NotificationManager
) {
}