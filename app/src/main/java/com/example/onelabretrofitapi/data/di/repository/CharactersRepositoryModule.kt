package com.example.onelabretrofitapi.data.di.repository

import com.example.onelabretrofitapi.core.NetworkChecker
import com.example.onelabretrofitapi.data.api.CharactersApi
import com.example.onelabretrofitapi.data.repository.datasource.local.LocalDataSource
import com.example.onelabretrofitapi.data.paging.CharacterPagingSource
import com.example.onelabretrofitapi.data.repository.datasource.remote.RemoteDataSource
import com.example.onelabretrofitapi.domain.repository.CharactersRepository
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
    fun providePagingSource(api: CharactersApi) = CharacterPagingSource(api)

    @Provides
    @Singleton
    fun provideCharactersRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        pagingSource: CharacterPagingSource,
        networkChecker: NetworkChecker
    ): CharactersRepository = CharactersRepositoryImpl(remoteDataSource, localDataSource, pagingSource, networkChecker)
}