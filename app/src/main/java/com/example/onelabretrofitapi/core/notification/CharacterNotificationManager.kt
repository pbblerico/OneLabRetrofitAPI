package com.example.onelabretrofitapi.core.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import com.example.onelabretrofitapi.presentation.MainActivity
import com.example.onelabretrofitapi.utils.createNotification
import com.example.onelabretrofitapi.utils.getPendingIntent
import java.util.UUID
import javax.inject.Inject

class CharacterNotificationManager @Inject constructor(
    private val context: Context,
    private val notificationManager: NotificationManager
) {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    fun showNotification(characterNotification: CharacterNotification) {
        val pendingIntent = context.getPendingIntent(MainActivity::class.java)
        buildNotificationChannel(characterNotification)
        val notification = context.createNotification(
            characterNotification = characterNotification,
            pendingIntent = pendingIntent
        )
        notificationManager.notify(
            System.currentTimeMillis().toInt(),
            notification
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buildNotificationChannel(characterNotidication: CharacterNotification) {
        val channel = NotificationChannel(
            characterNotidication.channelId,
            context.getString(characterNotidication.channelName),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.lightColor = Color.BLUE
        if(notificationManager.notificationChannels.all { it.id != characterNotidication.channelId }) {
            notificationManager.createNotificationChannel(channel)
        }
    }
}

data class CharacterNotification(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val text: String,
    val channelId: String = UUID.randomUUID().toString(),
    @StringRes val channelName: Int,
    @DrawableRes val icon: Int,
    @StringRes val channelDescription: Int
)