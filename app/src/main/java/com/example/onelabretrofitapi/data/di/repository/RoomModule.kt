package com.example.onelabretrofitapi.data.di.repository

import android.content.Context
import androidx.room.Room
import com.example.onelabretrofitapi.data.local.CharacterDao
import com.example.onelabretrofitapi.data.DATABASE_NAME_CHARACTERS
import com.example.onelabretrofitapi.data.local.CacheDao
import com.example.onelabretrofitapi.data.local.MyRoomDatabase
import com.example.onelabretrofitapi.data.repository.datasource.local.LocalDataSource
import com.example.onelabretrofitapi.data.repository.datasource.local.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): MyRoomDatabase {
        return Room
            .databaseBuilder(context, MyRoomDatabase::class.java, DATABASE_NAME_CHARACTERS)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCharacterDao(db: MyRoomDatabase): CharacterDao = db.charactersDao()

    @Provides
    fun provideCacheDao(db: MyRoomDatabase): CacheDao = db.cacheDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(characterDao: CharacterDao, cacheDao: CacheDao): LocalDataSource = LocalDataSourceImpl(characterDao, cacheDao)
}