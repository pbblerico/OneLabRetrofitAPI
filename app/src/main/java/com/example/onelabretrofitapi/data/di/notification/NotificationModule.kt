package com.example.onelabretrofitapi.data.di.notification

import android.app.NotificationManager
import android.content.Context
import com.example.onelabretrofitapi.core.notification.CharacterNotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {
    @Provides
    @Singleton
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @Provides
    @Singleton
    fun provideCharacterNotificationManager(
        @ApplicationContext context: Context,
        notificationManager: NotificationManager
    ): CharacterNotificationManager {
        return CharacterNotificationManager(context, notificationManager)
    }
}