package com.example.onelabretrofitapi.data.di.repository

import com.example.onelabretrofitapi.data.api.CharactersApi
import com.example.onelabretrofitapi.data.repository.CharactersRepository
import com.example.onelabretrofitapi.data.repository.CharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersRepositoryModule {
    @Provides
    @Singleton
    fun provideCharactersRepository(charactersApi: CharactersApi): CharactersRepository = CharactersRepositoryImpl(charactersApi)
}