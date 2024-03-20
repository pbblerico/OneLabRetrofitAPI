package com.example.onelabretrofitapi.data.di.repository

import com.example.onelabretrofitapi.data.api.CharactersApi
import com.example.onelabretrofitapi.data.repository.datasource.remote.CharacterPagingSource
import com.example.onelabretrofitapi.data.repository.datasource.remote.RemoteDataSource
import com.example.onelabretrofitapi.data.repository.datasource.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: CharactersApi): RemoteDataSource = RemoteDataSourceImpl(api)
}