package com.example.onelabretrofitapi.data.di.useCase

import com.example.onelabretrofitapi.domain.repository.CharactersRepository
import com.example.onelabretrofitapi.domain.useCase.CharacterInfoUseCase
import com.example.onelabretrofitapi.domain.useCase.CharacterInfoUseCaseImpl
import com.example.onelabretrofitapi.domain.useCase.CharacterListUseCase
import com.example.onelabretrofitapi.domain.useCase.CharacterListUseCaseImpl
import com.example.onelabretrofitapi.domain.useCase.CharacterPagingDataUseCase
import com.example.onelabretrofitapi.domain.useCase.CharacterPagingDataUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object CharacterUseCaseModule {

    @Provides
    fun provideCharacterListUseCase(repo: CharactersRepository): CharacterListUseCase = CharacterListUseCaseImpl(repo)

    @Provides
    fun provideCharacterInfoUseCase(repo: CharactersRepository): CharacterInfoUseCase = CharacterInfoUseCaseImpl(repo)

    @Provides
    fun provideCharacterPagingDataUseCase(repo: CharactersRepository): CharacterPagingDataUseCase = CharacterPagingDataUseCaseImpl(repo)
}