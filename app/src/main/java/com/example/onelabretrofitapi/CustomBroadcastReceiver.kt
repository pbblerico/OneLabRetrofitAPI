package com.example.onelabretrofitapi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.onelabretrofitapi.core.notification.CharacterNotification
import com.example.onelabretrofitapi.core.notification.CharacterNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CustomBroadcastReceiver : BroadcastReceiver() {
    @Inject
    lateinit var characterNotificationManager: CharacterNotificationManager
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == "TEST_ACTION") {
            Log.d("re", "noti")
            characterNotificationManager.showNotification(
                CharacterNotification(
                    title = "Checkout New Character!",
                    text = "Let's explore new characters of Rick & Morty Universe",
                    channelId = "update cache",
                    channelName = R.string.character_update_cache_channel,
                    icon = R.mipmap.ic_launcher,
                    channelDescription = R.string.character_update_cache_channel_desc
                )
            )
        }
    }
}