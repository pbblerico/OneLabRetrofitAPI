package com.example.onelabretrofitapi.data.di.repository

import android.content.Context
import androidx.room.Room
import com.example.onelabretrofitapi.data.db.CharacterDao
import com.example.onelabretrofitapi.data.db.DATABASE_NAME_CHARACTERS
import com.example.onelabretrofitapi.data.db.MyRoomDatabase
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
    @Singleton
    fun provideLocalDataSource(dao: CharacterDao): LocalDataSource = LocalDataSourceImpl(dao)
}